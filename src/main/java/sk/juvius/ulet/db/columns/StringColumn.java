package sk.juvius.ulet.db.columns;

import sk.juvius.ulet.db.DataType;

public class StringColumn extends AbstractColumn {
    public StringColumn(String columnName) {
        super(columnName, DataType.STRING);
    }
}
