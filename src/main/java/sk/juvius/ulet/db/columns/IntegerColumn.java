package sk.juvius.ulet.db.columns;

import sk.juvius.ulet.db.DataType;

public class IntegerColumn extends AbstractColumn {
    public IntegerColumn(String name) {
        super(name, DataType.INTEGER);
    }
}
