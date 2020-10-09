package sk.juvius.ulet.ui.table;

import javax.swing.table.*;
import java.util.List;

public class DynamicTableModel<T> extends AbstractTableModel {

    private final List<T> items;
    private final List<ColumnInfo> columns;

    public DynamicTableModel(List<ColumnInfo> columns, List<T> items) {
        this.items = items;
        this.columns = columns;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }


    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clazz = columns.get(columnIndex).getColumnClass();
        return clazz == null ? Object.class : clazz;
    }

    @Override
    public int findColumn(String columnName) {
        for(int i = 0; i < columns.size(); i++) {
            ColumnInfo col = columns.get(i);
            if(col.getName().equals(columnName)) return i;
        }
        return -1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return columns.get(col).getResolverValue(row, col);
    }

    public ColumnInfo getColumn(int i) {
        return columns.get(i);
    }

    public void addRow(T item) {
        items.add(item);
        int i = items.size() - 1;
        fireTableRowsInserted(i, i);
    }

    public void removeRow(int row) {
        items.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void addColumn(ColumnInfo col) {
        columns.add(col);
        fireTableStructureChanged();
    }

    public void removeColumn(ColumnInfo col) {
        columns.remove(col);
        fireTableStructureChanged();
    }

    public int getColumnIndex(ColumnInfo ci) {
        for(int i = 0; i < columns.size(); i++) {
            if(columns.get(i) == ci) return i;
        }
        return -1;
    }
}
