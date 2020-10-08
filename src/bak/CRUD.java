package sk.juvius.ulet.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.db.columns.Column;
import sk.juvius.ulet.db.columns.IntegerColumn;
import sk.juvius.ulet.db.columns.StringColumn;
import sk.juvius.ulet.db.values.IntegerValue;
import sk.juvius.ulet.db.values.StringValue;
import sk.juvius.ulet.db.values.Value;

import java.sql.*;
import java.util.*;

public class CRUD {

    private static final Logger log = LoggerFactory.getLogger(CRUD.class);
    private final DBManager manager;
    private QueryBuilder queryBuilder;

    public CRUD(DBManager manager) {
        this.manager = manager;
    }

    public ReadBuilder select(Column... columns) {
        queryBuilder = new QueryBuilder();
        queryBuilder.append("SELECT ");
        if(columns.length == 0) {
            queryBuilder.append("*");
        } else {
            queryBuilder.addColumns(columns);
        }
        return new ReadBuilder();
    }

//    public ReadBuilder selectAll() {
//        queryBuilder = new QueryBuilder();
//        queryBuilder.append("SELECT *");
//        return new ReadBuilder();
//    }

    public CreateBuilder insertInto(String table, Column... columns) {
        queryBuilder = new QueryBuilder();
        queryBuilder.append("INSERT INTO ").append(table).append(" (");
        queryBuilder.addColumns(columns);
        queryBuilder.append(") ");
        return new CreateBuilder();
    }

    public UpdateBuilder update(String table) {
        queryBuilder = new QueryBuilder();
        queryBuilder.append("UPDATE ").append(table).append(" ");
        return new UpdateBuilder();
    }

    public DeleteBuilder deleteFrom(String table) {
        queryBuilder = new QueryBuilder();
        queryBuilder.append("DELETE FROM ").append(table).append(" ");
        return new DeleteBuilder();
    }

    private void setColumns(ResultSet resultSet) throws SQLException {
        if(resultSet != null) {
            ResultSetMetaData meta = resultSet.getMetaData();
            int columnCount = meta.getColumnCount();
            for(int i = 1; i <= columnCount; i++) {
                String tableName = queryBuilder.addTableNames ? meta.getTableName(i) + "." : "";
                String colName = meta.getColumnName(i);
                int colType = meta.getColumnType(i);
                Column col = null;
                switch (colType) {
                    case 4: col = new IntegerColumn(tableName + colName); break;
                    case -9:
                    case 12:
                        col = new StringColumn(tableName + colName); break;
                }
                if(col != null) queryBuilder.columns.add(col);
            }
        }
    }

    private Statement execute(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(queryBuilder.getSql());
        log.debug(queryBuilder.getSql());
        for(int i = 0; i < queryBuilder.params.size(); i++) {
            Value value = queryBuilder.params.get(i);
            log.debug(i + 1 + ": " + value.toString());
            switch (value.getDataType()) {
                case STRING: statement.setString(i + 1, ((StringValue) value).getValue()); break;
                case INTEGER: statement.setInt(i + 1, ((IntegerValue) value).getValue()); break;
            }
        }
        statement.execute();
        return statement;
    }

    private List<RowData> query(ResultSet resultSet) throws SQLException {
        List<RowData> list  = new ArrayList<>();
        if (resultSet != null) {
            if(queryBuilder.columns.size() == 0) setColumns(resultSet);
            while (resultSet.next()) {
                RowData row = new RowData();
                for(Column column : queryBuilder.getColumns()) {
                    Value value = null;
                    String colName = column.getColumnName();
                    row.getColumnMap().put(colName, column);
                    switch (column.getDataType()) {
                        case STRING: value = new StringValue(resultSet.getString(colName)); break;
                        case INTEGER: value = new IntegerValue(resultSet.getInt(colName)); break;
                    }
                    row.put(column, value);
                }
                list.add(row);
            }
        }
        return list;
    }

    private static final class QueryBuilder {
        private final StringBuilder sql;
        private final List<Value> params = new ArrayList<>();
        private final List<Column> columns = new ArrayList<>();
        private String table;
        private boolean addTableNames = false;

        public QueryBuilder() {
            this.sql = new StringBuilder();
        }

        public void addColumns(Column[] columns) {
            for(int i = 0; i < columns.length; i++) {
                sql.append(columns[i].getColumnName());
                this.columns.add(columns[i]);
                if(i < columns.length - 1) {
                    sql.append(", ");
                }
            }
        }

        public List<Column> getColumns() {
            return columns;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getTable() {
            return table;
        }

        public StringBuilder append(String token) {
            sql.append(token);
            return sql;
        }

        public String getSql() {
            return sql.toString();
        }
    }

    public class ReadBuilder {

        public ReadBuilder from(String table) {
            queryBuilder.append(" FROM ").append(table).append(" ");
            return this;
        }

        public ReadBuilder whereEq(IntegerColumn col, IntegerValue val) {
            return whereEq(col.getColumnName(), val);
        }

        public ReadBuilder whereEq(StringColumn col, StringValue val) {
            return whereEq(col.getColumnName(), val);
        }

        public ReadBuilder whereEq(String param, Value value) {
            queryBuilder.append("WHERE ").append(param).append(" =? ");
            queryBuilder.params.add(value);
            return this;
        }

        public ReadBuilder innerJoin(String param) {
            queryBuilder.append("INNER JOIN ").append(param).append(" ");
            queryBuilder.addTableNames = true;
            return this;
        }

        public ReadBuilder leftJoin(String param) {
            queryBuilder.append("LEFT JOIN ").append(param).append(" ");
            queryBuilder.addTableNames = true;
            return this;
        }

        public ReadBuilder onEq(String param1, String param2) {
            queryBuilder.append("ON ").append(param1).append("=").append(param2).append(" ");
            return this;
        }

        public ReadBuilder orEq(String param, Value value) {
            queryBuilder.append("OR ").append(param).append(" =? ");
            queryBuilder.params.add(value);
            return this;
        }

        public ReadBuilder andEq(String param, Value value) {
            queryBuilder.append("AND ").append(param).append(" =? ");
            queryBuilder.params.add(value);
            return this;
        }

        public ReadBuilder orderBy(Column column, boolean desc) {
            return orderBy(column.getColumnName(), desc);
        }

        public ReadBuilder orderBy(Column column) {
            return orderBy(column.getColumnName(), false);
        }

        public ReadBuilder orderBy(String columnName) {
            return orderBy(columnName, false);
        }

        public ReadBuilder orderBy(String columnName, boolean desc) {
            queryBuilder.append("ORDER BY ").append(columnName).append(" ");
            if(desc) {
                queryBuilder.append("DESC");
            }
            return this;
        }

        public List<RowData> fetch() throws SQLException {
            Connection conn = manager.getConnection();
            Statement statement = execute(conn);
            ResultSet rs = statement.getResultSet();
            List<RowData> list = query(rs);
            rs.close();
            statement.close();
            conn.close();
            return list;
        }
    }

    public class CreateBuilder {
        public CreateBuilder values(Value... values) {
            queryBuilder.append("VALUES (" );
            for(int i = 0; i < values.length; i++) {
                queryBuilder.params.add(values[i]);
                queryBuilder.append("?");
                if(i < values.length - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(")" );
            return this;
        }

        public void commit() throws SQLException {
            Connection conn = manager.getConnection();
            Statement statement = execute(conn);
            statement.close();
            conn.close();
        }
    }

    public class UpdateBuilder {
        private boolean setKeyword = false;
        public UpdateBuilder set(Column column, Value value) {
            if(!setKeyword) {
                queryBuilder.append("SET ");
                setKeyword = true;
            } else {
                queryBuilder.append(", ");
            }
            queryBuilder.append(column.getColumnName()).append("=?");
            queryBuilder.params.add(value);
            return this;
        }

        public void commit() throws SQLException {
            Connection conn = manager.getConnection();
            Statement statement = execute(conn);
            statement.close();
            conn.close();
        }

        public UpdateBuilder where(String param) {
            queryBuilder.append(" WHERE ").append(param);
            return this;
        }

        public UpdateBuilder eq(Value value) {
            queryBuilder.params.add(value);
            queryBuilder.append("=").append("? ");
            return this;
        }

        public UpdateBuilder eq(String value) {
            return eq(new StringValue(value));
        }

        public UpdateBuilder eq(int value) {
            return eq(new IntegerValue(value));
        }
    }

    public class DeleteBuilder {
        public DeleteBuilder where(String param) {
            queryBuilder.append("WHERE ").append(param);
            return this;
        }

        public DeleteBuilder eq(Value value) {
            queryBuilder.params.add(value);
            queryBuilder.append("=").append("? ");
            return this;
        }

        public void commit() throws SQLException {
            Connection conn = manager.getConnection();
            Statement statement = execute(conn);
            statement.close();
            conn.close();
        }
    }
}
