package sk.juvius.ulet.db;

import org.sqlite.JDBC;
import sk.juvius.ulet.AppContext;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private final String dbPath;

    static {
        System.setProperty("org.sqlite.lib.path", AppContext.getRoot());
        System.setProperty("org.sqlite.lib.name", "sqlitejdbc.dll");
        try {
            DriverManager.registerDriver(new JDBC());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DBManager(String dbPath) {
        this.dbPath = dbPath;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }
}
