package sk.juvius.ulet.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.db.columns.AbstractColumn;

import java.sql.SQLException;

public class CRUD {

    private static final Logger log = LoggerFactory.getLogger(CRUD.class);

    private final QueryBuilder queryBuilder;

    public CRUD(DBManager manager) {
        this.queryBuilder = new QueryBuilder(manager);
    }

    public QueryBuilder select(AbstractColumn... columns) {
        return select(false, columns);
    }

    public QueryBuilder selectDistinct(AbstractColumn... columns) {
        return select(true, columns);
    }

    public QueryBuilder select(boolean distinct, AbstractColumn... columns) {
        queryBuilder.append("SELECT ");
        if(distinct) queryBuilder.append("DISTINCT ");
        if(columns.length == 0) {
            queryBuilder.append("*");
        } else {
            queryBuilder.addColumns(columns);
        }
        return queryBuilder;
    }

    public QueryBuilder insertInto(String table, AbstractColumn... columns) {
        queryBuilder.append("INSERT INTO ").append(table).append(" (");
        queryBuilder.addColumns(columns);
        queryBuilder.append(") ");
        return queryBuilder;
    }

    public QueryBuilder update(String table) {
        queryBuilder.append("UPDATE ").append(table).append(" ");
        return queryBuilder;
    }

    public QueryBuilder deleteFrom(String table) {
        queryBuilder.append("DELETE FROM ").append(table).append(" ");
        return queryBuilder;
    }

    public void close() throws SQLException {
        queryBuilder.getConnection().close();
    }

    public void setAutoClose(boolean autoClose) {
        queryBuilder.setAutoClose(autoClose);
    }

    public boolean isAutoClose() {
        return queryBuilder.isAutoClose();
    }

    public boolean isClosed() throws SQLException {
        return queryBuilder.getConnection().isClosed();
    }
}
