package sk.juvius.ulet.commands.login.service;

import com.password4j.Password;
import com.password4j.SecureString;
import sk.juvius.ulet.db.Crud;
import sk.juvius.ulet.db.RowData;
import sk.juvius.ulet.db.columns.IntegerColumn;
import sk.juvius.ulet.db.columns.StringColumn;
import sk.juvius.ulet.db.values.StringValue;
import sk.juvius.ulet.commands.login.model.User;

import java.sql.*;
import java.util.List;

public class UserService {

    private final Crud crud;
    private final String tableName = "users";
    private final StringColumn hashCol = new StringColumn("hash");
    private final StringColumn nameCol = new StringColumn("name");
    private final IntegerColumn idCol = new IntegerColumn("id");

    public UserService(Crud crud) {
        this.crud = crud;
    }

    public boolean verify(User user, char[] password) {
        String hashed = user.getHash();
        SecureString sec = new SecureString(password);
        boolean verified = Password.check(sec, hashed).withSCrypt();
        sec.clear();
        return verified;
    }

    public User getUserByName(String userName) {
        StringValue nameVal = new StringValue(userName);
        List<RowData> data;
        try {
            data = crud
                    .select(idCol, hashCol)
                    .from(tableName)
                    .where("name")
                    .eq(nameVal)
                    .fetchRows();
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
