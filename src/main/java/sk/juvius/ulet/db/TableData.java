package sk.juvius.ulet.db;

import sk.juvius.ulet.db.columns.Column;
import sk.juvius.ulet.db.columns.IntegerColumn;
import sk.juvius.ulet.db.columns.StringColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableData extends ArrayList<RowData> {

    final Map<String, Column> columns = new HashMap<>();

    public Column getColumn(String name) {
        return columns.get(name);
    }

    public StringColumn getStringColumn(String name) {
        return (StringColumn) getColumn(name);
    }

    public IntegerColumn getIntegerColumn(String name) {
        return (IntegerColumn) getColumn(name);
    }
}
