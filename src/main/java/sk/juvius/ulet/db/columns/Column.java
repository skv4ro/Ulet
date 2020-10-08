package sk.juvius.ulet.db.columns;

import sk.juvius.ulet.db.DataType;

public interface Column {
    String getColumnName();
    DataType getDataType();
}
