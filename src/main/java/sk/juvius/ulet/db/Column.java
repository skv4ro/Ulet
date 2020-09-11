package sk.juvius.ulet.db;

public abstract class Column {
    private final String columnName;
    private final ColumnDataType type;

    public Column(String columnName, ColumnDataType type) {
        this.columnName = columnName;
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnDataType getType() {
        return type;
    }
}
