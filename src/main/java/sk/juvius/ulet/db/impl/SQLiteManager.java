package sk.juvius.ulet.db.impl;

import org.sqlite.JDBC;
import sk.juvius.ulet.R;
import sk.juvius.ulet.db.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager implements DBManager {

    private final String dbPath;

    static {
        System.setProperty("org.sqlite.lib.path", R.getResPath());
        System.setProperty("org.sqlite.lib.name", "sqlitejdbc.dll");
        try {
            DriverManager.registerDriver(new JDBC());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLiteManager(String dbPath) {
        this.dbPath = dbPath;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }
}
