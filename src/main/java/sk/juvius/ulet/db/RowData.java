package sk.juvius.ulet.db;

import sk.juvius.ulet.db.columns.Column;
import sk.juvius.ulet.db.columns.IntegerColumn;
import sk.juvius.ulet.db.columns.StringColumn;
import sk.juvius.ulet.db.values.IntegerValue;
import sk.juvius.ulet.db.values.StringValue;
import sk.juvius.ulet.db.values.Value;

import java.util.HashMap;

public class RowData extends HashMap<Column, Value> {

    public String getString(StringColumn column) {
        Value value = get(column);
        if(value == null) return null;
        return ((StringValue) value).getValue();
    }

    public Integer getInteger(IntegerColumn column) {
        Value value = get(column);
        if(value == null) return null;
        return ((IntegerValue) value).getValue();
    }
}
