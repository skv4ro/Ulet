package sk.juvius.ulet.db.columns;

import sk.juvius.ulet.db.DataType;

public class IntegerColumn extends Column {
    public IntegerColumn(String name) {
        super(name, DataType.INTEGER);
    }
}
