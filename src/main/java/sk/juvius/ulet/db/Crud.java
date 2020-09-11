package sk.juvius.ulet.db;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    int test() throws SQLException {
        Connection conn = manager.getConnection();
        Statement statement = conn.createStatement();
        statement.execute("select name, id from users");
        ResultSet rs = statement.getResultSet();
        return rs.getInt("id");
    }

    private static class QueryBuilder {
        private final StringBuilder query;
        private final List<Column> columns = new ArrayList<>();
        private String table;

        public QueryBuilder() {
            this.query = new StringBuilder();
        }

        public void addColumns(Column[] columns) {
            if(columns.length == 1 && columns[0].equals("*")) {
                query.append("* ");
            } else {
                for(int i = 0; i < columns.length; i++) {
                    query.append(columns[i].getColumnName());
                    this.columns.add(columns[i]);
                    if(i < columns.length - 1) {
                        query.append(", ");
                    } else {
                        query.append(" ");
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
            query.append(token);
            return query;
        }

        public String getQuery() {
            return query.toString();
        }
    }

    public class ReadBuilder {

        public ReadBuilder from(String table) {
            queryBuilder.append("FROM ").append(table).append(" ");
            return this;
        }

        public ReadBuilder where(String param) {
            queryBuilder.append("WHERE ").append(param);
            return this;
        }

        public ReadBuilder equals(String value) {
            queryBuilder.append("=").append("\"").append(value).append("\"");
            return this;
        }

        public ReadBuilder equals(int value) {
            queryBuilder.append("=").append(value).append(" ");
            return this;
        }

        public ReadBuilder or(String param) {
            queryBuilder.append("OR ").append(param);
            return this;
        }

        public Map<String, List<Object>> fetch() throws SQLException {
            Connection conn = manager.getConnection();
            Statement statement = conn.createStatement();
            System.out.println(queryBuilder.getQuery());
            statement.execute(queryBuilder.getQuery());
            ResultSet rs = statement.getResultSet();
            Map<String, List<Object>> map = new HashMap<>();
            for(Column column : queryBuilder.getColumns()) {
                map.put(column.getColumnName(), new ArrayList<>());
            }
            if (rs != null) {
                while (rs.next()) {
                    for(Column column : queryBuilder.getColumns()) {
                        switch (column.getType()) {
                            case STRING: map.get(column.getColumnName()).add(rs.getString(column.getColumnName())); break;
                            case INTEGER: map.get(column.getColumnName()).add(rs.getInt(column.getColumnName())); break;
                            case DOUBLE: map.get(column.getColumnName()).add(rs.getDouble(column.getColumnName())); break;
                        }
                    }
                }
            }
            statement.close();
            conn.close();
            return map;
        }
    }

    public static void main(String[] args) throws SQLException {
        Crud crud = new Crud(new DBManager(new File("c:/users/skvarkaj/documents/ulet/db.db").getAbsolutePath()));
        Map<String, List<Object>> map = crud
                .select(new StringColumn("name"))
                .from("users")
                .where("id")
                .equals(2)
                .or("id")
                .equals(3)
                .fetch();
        System.out.println(map.toString());

    }
}
