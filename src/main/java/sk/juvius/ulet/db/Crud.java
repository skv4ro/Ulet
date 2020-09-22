package sk.juvius.ulet.db;

import sk.juvius.ulet.db.columns.Column;
import sk.juvius.ulet.db.columns.IntegerColumn;
import sk.juvius.ulet.db.impl.MySQLManager;
import sk.juvius.ulet.db.impl.SQLiteManager;
import sk.juvius.ulet.db.values.IntegerValue;
import sk.juvius.ulet.db.values.StringValue;
import sk.juvius.ulet.db.values.Value;

import java.io.File;
import java.sql.*;
import java.util.*;

public class Crud {

    private final DBManager manager;
    private QueryBuilder queryBuilder;

    public Crud(DBManager manager) {
        this.manager = manager;
    }

    public ReadBuilder select(Column... columns) {
        queryBuilder = new QueryBuilder();
        queryBuilder.append("SELECT ");
        queryBuilder.addColumns(columns);
        return new ReadBuilder();
    }

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

    private Statement execute(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(queryBuilder.getSql());
        for(int i = 0; i < queryBuilder.params.size(); i++) {
            Value value = queryBuilder.params.get(i);
            switch (value.getDataType()) {
                case STRING: statement.setString(i + 1, ((StringValue) value).getValue()); break;
                case INTEGER: statement.setInt(i + 1, ((IntegerValue) value).getValue()); break;
            }
        }
        statement.execute();
        return statement;
    }

    private Map<Column, ColumnData> queryColumns(ResultSet resultSet) throws SQLException {
        Map<Column, ColumnData> map = new HashMap<>();
        for(Column column : queryBuilder.getColumns()) {
            map.put(column, new ColumnData());
        }
        if (resultSet != null) {
            while (resultSet.next()) {
                for(Column column : queryBuilder.getColumns()) {
                    List<Object> dbCol = map.get(column);
                    String colName = column.getColumnName();
                    switch (column.getDataType()) {
                        case STRING: dbCol.add(resultSet.getString(colName)); break;
                        case INTEGER: dbCol.add(resultSet.getInt(colName)); break;
                        case DOUBLE: dbCol.add(resultSet.getDouble(colName)); break;
                        case DATE: dbCol.add(resultSet.getDate(colName)); break;
                        case BYTE: dbCol.add(resultSet.getByte(colName)); break;
                    }
                }
            }
        }
        return map;
    }

    private List<RowData> queryRows(ResultSet resultSet) throws SQLException {
        List<RowData> list  = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                RowData row = new RowData();
                for(Column column : queryBuilder.getColumns()) {
                    Value value = null;
                    String colName = column.getColumnName();
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
        private final List<Column> columns = new ArrayList<>();
        private final List<Value> params = new ArrayList<>();
        private String table;

        public QueryBuilder() {
            this.sql = new StringBuilder();
        }

        public void addColumns(Column[] columns) {
            if(columns.length == 1 && columns[0].getColumnName().equals("*")) {
                sql.append("* ");
            } else {
                for(int i = 0; i < columns.length; i++) {
                    sql.append(columns[i].getColumnName());
                    this.columns.add(columns[i]);
                    if(i < columns.length - 1) {
                        sql.append(", ");
                    }
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

        public ReadBuilder where(String param) {
            queryBuilder.append("WHERE ").append(param);
            return this;
        }

        public ReadBuilder eq(Value value) {
            queryBuilder.params.add(value);
            queryBuilder.append("=").append("? ");
            return this;
        }

        public ReadBuilder or(String param) {
            queryBuilder.append("OR ").append(param);
            return this;
        }

        public ReadBuilder and(String param) {
            queryBuilder.append("AND ").append(param);
            return this;
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

        public Map<Column, ColumnData> fetchColumns() throws SQLException {
            Connection conn = manager.getConnection();
            Statement statement = execute(conn);
            ResultSet rs = statement.getResultSet();
            Map<Column, ColumnData> map = queryColumns(rs);
            rs.close();
            statement.close();
            conn.close();
            return map;
        }

        public List<RowData> fetchRows() throws SQLException {
            Connection conn = manager.getConnection();
            Statement statement = execute(conn);
            ResultSet rs = statement.getResultSet();
            List<RowData> list = queryRows(rs);
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

    public static void main(String[] args) throws SQLException {
        //Crud crud = new Crud(new SQLiteManager(new File("c:/users/skvarkaj/documents/ulet/db.db").getAbsolutePath()));
        Crud crud = new Crud(new MySQLManager("localhost", 3306, "testdb", "root", "admin"));
        IntegerColumn nameCol = new IntegerColumn("id");
        List<RowData> user = crud
                .select(nameCol)
                .from("users")
                .orderBy("id")
                .fetchRows();
        System.out.println(user.size());
    }
}
