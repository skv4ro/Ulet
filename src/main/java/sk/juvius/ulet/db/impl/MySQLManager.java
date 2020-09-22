package sk.juvius.ulet.db.impl;

import sk.juvius.ulet.db.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager implements DBManager {

    private final String url;
    private final String user;
    private final String pass;

    public MySQLManager(String address, int port, String db, String user, String pass) {
        this.url = "jdbc:mysql://" + address + ":" + port + "/" + db;
        this.user = user;
        this.pass = pass;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url + "?serverTimezone=UTC", user, pass);
    }
}
