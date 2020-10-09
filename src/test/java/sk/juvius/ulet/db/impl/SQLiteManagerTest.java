package sk.juvius.ulet.db.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sk.juvius.ulet.db.CRUD;
import sk.juvius.ulet.db.DBManager;
import sk.juvius.ulet.db.RowData;
import sk.juvius.ulet.db.TableData;
import sk.juvius.ulet.db.columns.StringColumn;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteManagerTest {

    @Disabled
    @Test
    void test() throws SQLException {
        DBManager manager = new SQLiteManager("src/test/resources/db.db");
        CRUD crud = new CRUD(manager);
        TableData data = crud.select().from("users").orderBy("name").fetch();
        StringColumn nameCol = data.getStringColumn("name");
        assertEquals("david", data.get(0).getString(nameCol));
    }
}
