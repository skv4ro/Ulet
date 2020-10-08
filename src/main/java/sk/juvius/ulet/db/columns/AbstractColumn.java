package sk.juvius.ulet.db.columns;

import sk.juvius.ulet.db.DataType;

public abstract class AbstractColumn implements Column {
    protected final String columnName;
    protected final DataType type;

    public AbstractColumn(String columnName, DataType type) {
        this.columnName = columnName;
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public DataType getDataType() {
        return type;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", type=" + type +
                '}';
    }
}
