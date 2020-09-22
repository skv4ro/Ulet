package sk.juvius.ulet.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColumnData extends ArrayList<Object> {
    public int getInt(int id) {
        Object o = get(id);
        if(o instanceof Integer) {
            return (int) o;
        }
        throw new NumberFormatException("Object is not instance of integer!");
    }

    public String getString(int id) {
        Object o = get(id);
        if(o instanceof String) {
            return (String) o;
        }
        return null;
    }
}
