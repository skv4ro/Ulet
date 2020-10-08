package sk.juvius.ulet.service;

import sk.juvius.ulet.model.User;

import java.sql.SQLException;

public interface UserService {
    User getUserByName(String userName);
    void insertUserIntoDB(User user) throws SQLException;
}
