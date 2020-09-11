package sk.juvius.ulet.db;

public class StringColumn extends Column {

    public StringColumn(String columnName) {
        super(columnName, ColumnDataType.STRING);
    }
}
