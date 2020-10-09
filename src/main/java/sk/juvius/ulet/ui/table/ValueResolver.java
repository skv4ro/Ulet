package sk.juvius.ulet.ui.table;

public interface ValueResolver {
    Object getValue(int row, int col);
    default String getString(int row, int col) {
        Object value = getValue(row, col);
        if(value == null) return null;
        else return value.toString();
    } //TODO pospekulovat ci sa to takto da
}
