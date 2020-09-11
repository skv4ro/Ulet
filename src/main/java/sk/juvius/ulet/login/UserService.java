package sk.juvius.ulet.login;

import sk.juvius.ulet.db.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final DBManager manager;

    public UserService(DBManager manager) {
        this.manager = manager;
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = manager.getConnection();
        Statement s = conn.createStatement();
        s.execute("select * from users");
        ResultSet rs = s.getResultSet();
        if (rs != null) {
            while (rs.next()) {
                String name = rs.getString("name");
                String pass = rs.getString("password");
                users.add(new User(name, pass));
            }
        }
        s.close();
        conn.close();
        return users;
    }
}
