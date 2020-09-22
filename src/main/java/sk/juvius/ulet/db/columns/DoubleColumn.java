package sk.juvius.ulet.db.columns;

import sk.juvius.ulet.db.DataType;

public class DoubleColumn extends Column {
    public DoubleColumn(String columnName) {
        super(columnName, DataType.DOUBLE);
    }
}
