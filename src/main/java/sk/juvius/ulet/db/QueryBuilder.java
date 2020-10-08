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
import java.util.ArrayList;
import java.util.List;

public final class QueryBuilder {

    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    private final StringBuilder sql = new StringBuilder();
    private final List<Value> params = new ArrayList<>();
    private final List<Column> columns = new ArrayList<>();
    private final DBManager manager;
    private Connection connection;
    private boolean addTableNames = false;
    private boolean setKeywordAdded = false;
    private boolean autoClose = true;

    QueryBuilder(DBManager manager) {
        this.manager = manager;
    }

    public StringBuilder append(String token) {
        sql.append(token);
        return sql;
    }

    public QueryBuilder from(String table) {
        sql.append(" FROM ").append(table).append(" ");
        return this;
    }

    public QueryBuilder whereEq(IntegerColumn col, IntegerValue val) {
        return whereEq(col.getColumnName(), val);
    }

    public QueryBuilder whereEq(StringColumn col, StringValue val) {
        return whereEq(col.getColumnName(), val);
    }

    public QueryBuilder whereEq(String param, Value value) {
        sql.append("WHERE ").append(param).append(" =? ");
        params.add(value);
        return this;
    }

    public QueryBuilder whereLike(StringColumn param, StringValue value) {
        sql.append("WHERE ").append(param.getColumnName()).append(" LIKE ? ");
        params.add(value);
        return this;
    }

    public QueryBuilder whereIsNotNull(StringColumn param) {
        sql.append("WHERE ").append(param.getColumnName()).append(" IS NOT NULL ");
        return this;
    }

    public QueryBuilder whereIsNull(StringColumn param, StringValue value) {
        sql.append("WHERE ").append(param.getColumnName()).append(" IS NULL ");
        params.add(value);
        return this;
    }

    public QueryBuilder innerJoin(String param) {
        sql.append("INNER JOIN ").append(param).append(" ");
        addTableNames = true;
        return this;
    }

    public QueryBuilder leftJoin(String param) {
        sql.append("LEFT JOIN ").append(param).append(" ");
        addTableNames = true;
        return this;
    }

    public QueryBuilder onEq(String param1, String param2) {
        sql.append("ON ").append(param1).append("=").append(param2).append(" ");
        return this;
    }

    public QueryBuilder orEq(String param, Value value) {
        sql.append("OR ").append(param).append(" =? ");
        params.add(value);
        return this;
    }

    public QueryBuilder andEq(String param, Value value) {
        sql.append("AND ").append(param).append(" =? ");
        params.add(value);
        return this;
    }

    public QueryBuilder orderBy(Column column, boolean desc) {
        return orderBy(column.getColumnName(), desc);
    }

    public QueryBuilder orderBy(Column column) {
        return orderBy(column.getColumnName(), false);
    }

    public QueryBuilder orderBy(String columnName) {
        return orderBy(columnName, false);
    }

    public QueryBuilder orderBy(String columnName, boolean desc) {
        sql.append("ORDER BY ").append(columnName).append(" ");
        if(desc) {
            sql.append("DESC");
        }
        return this;
    }

    public QueryBuilder values(Value... values) {
        sql.append("VALUES (" );
        for(int i = 0; i < values.length; i++) {
            params.add(values[i]);
            sql.append("?");
            if(i < values.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")" );
        return this;
    }

    public QueryBuilder set(Column column, Value value) {
        if(!setKeywordAdded) {
            sql.append("SET ");
            setKeywordAdded = true;
        } else {
            sql.append(", ");
        }
        sql.append(column.getColumnName()).append("=?");
        params.add(value);
        return this;
    }

    public QueryBuilder limit(int limit) {
        sql.append("LIMIT ").append(limit).append(" ");
        return this;
    }

    public TableData fetch() throws SQLException {
        connection = manager.getConnection();
        Statement statement = execute(connection);
        ResultSet rs = statement.getResultSet();
        TableData list = query(rs);
        rs.close();
        statement.close();
        if(autoClose) connection.close();
        reset();
        return list;
    }

    public void commit() throws SQLException {
        connection = manager.getConnection();
        Statement statement = execute(connection);
        statement.close();
        if(autoClose) connection.close();
        reset();
    }

    void addColumns(Column[] columns) {
        for(int i = 0; i < columns.length; i++) {
            sql.append(columns[i].getColumnName());
            this.columns.add(columns[i]);
            if(i < columns.length - 1) {
                sql.append(", ");
            }
        }
    }

    private void reset() {
        sql.setLength(0);
        columns.clear();
        params.clear();
        addTableNames = false;
        setKeywordAdded = false;
    }

    private Statement execute(Connection connection) throws SQLException {
        String sqlString = sql.toString();
        PreparedStatement statement = connection.prepareStatement(sqlString);
        log.debug(sqlString);
        for(int i = 0; i < params.size(); i++) {
            Value value = params.get(i);
            log.debug(i + 1 + ": " + value.toString());
            switch (value.getDataType()) {
                case STRING: statement.setString(i + 1, ((StringValue) value).getValue()); break;
                case INTEGER: statement.setInt(i + 1, ((IntegerValue) value).getValue()); break;
            }
        }
        statement.execute();
        return statement;
    }

    private TableData query(ResultSet resultSet) throws SQLException {
        TableData data  = new TableData();
        if (resultSet != null) {
            if(columns.size() == 0) setColumns(resultSet);
            addColumns(data);
            while (resultSet.next()) {
                RowData row = new RowData();
                for(Column column : columns) {
                    Value value = null;
                    String colName = column.getColumnName();
                    switch (column.getDataType()) {
                        case STRING: value = new StringValue(resultSet.getString(colName)); break;
                        case INTEGER: value = new IntegerValue(resultSet.getInt(colName)); break;
                    }
                    row.put(column, value);
                }
                data.add(row);
            }
        }
        return data;
    }

    private void setColumns(ResultSet resultSet) throws SQLException {
        if(resultSet != null) {
            ResultSetMetaData meta = resultSet.getMetaData();
            int columnCount = meta.getColumnCount();
            for(int i = 1; i <= columnCount; i++) {
                String tableName = addTableNames ? meta.getTableName(i) + "." : "";
                String colName = meta.getColumnName(i);
                int colType = meta.getColumnType(i);
                Column col = null;
                switch (colType) {
                    case 4: col = new IntegerColumn(tableName + colName); break;
                    case -9:
                    case 12:
                        col = new StringColumn(tableName + colName); break;
                }
                if(col != null) {
                    columns.add(col);
                }
            }
        }
    }

    private void addColumns(TableData data) {
        for(Column column : columns) {
            data.columns.put(column.getColumnName(), column);
        }
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
    }

    public Connection getConnection() {
        return connection;
    }
}
