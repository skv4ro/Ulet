package sk.juvius.ulet.db.columns;

import sk.juvius.ulet.db.DataType;

public class DoubleColumn extends AbstractColumn {
    public DoubleColumn(String columnName) {
        super(columnName, DataType.DOUBLE);
    }
}
