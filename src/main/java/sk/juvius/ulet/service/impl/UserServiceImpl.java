package sk.juvius.ulet.service.impl;

import sk.juvius.ulet.db.CRUD;
import sk.juvius.ulet.db.RowData;
import sk.juvius.ulet.db.columns.IntegerColumn;
import sk.juvius.ulet.db.columns.StringColumn;
import sk.juvius.ulet.db.values.StringValue;
import sk.juvius.ulet.model.User;
import sk.juvius.ulet.service.UserService;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final CRUD crud;
    private final String tableName = "users";
    private final StringColumn hashCol = new StringColumn("hash");
    private final StringColumn nameCol = new StringColumn("name");
    private final IntegerColumn idCol = new IntegerColumn("id");

    public UserServiceImpl(CRUD crud) {
        this.crud = crud;
    }

    public User getUserByName(String userName) {
        StringValue nameVal = new StringValue(userName);
        List<RowData> data;
        try { //TODO upravit aby vyhadzovalo vynimku a v autologin ju cachovat
            data = crud
                    .select(idCol, hashCol)
                    .from(tableName)
                    .whereEq("name", nameVal)
                    .fetch();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(data.size() == 0) return null;
        int id = data.get(0).getInteger(idCol);
        String hashPass = data.get(0).getString(hashCol);
        return new User(id, userName, hashPass);
    }

    public void insertUserIntoDB(User user) throws SQLException {
        StringValue nameVal = new StringValue(user.getName());
        StringValue passVal = new StringValue(user.getHash());
        crud
                .insertInto(tableName, nameCol, hashCol)
                .values(nameVal, passVal)
                .commit();
    }
}
