package sk.juvius.ulet.ui.table;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Table<T> extends JTable {

    protected DynamicTableModel<T> model;
    protected List<ColumnInfo> columns = new ArrayList<>();
    protected List<T> data = new ArrayList<>();

    public Table() {
        getTableHeader().setDefaultRenderer(new HeaderCellRenderer());
        setDefaultRenderer(Object.class, new CellRenderer(gridColor));
        setDefaultRenderer(Integer.class, new CellRenderer(gridColor));
        setDefaultRenderer(Image.class, new ImageCellRenderer(gridColor));
        setRowHeight(20);
        setShowVerticalLines(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        setAutoCreateRowSorter(true);
    }

    public void addRow(T item) {
        model.addRow(item);
    }

    public void removeRow(int index) {
        model.removeRow(index);
    }

    public void addColumn(ColumnInfo ci) {
        model.addColumn(ci);
    }

    public void removeColumn(ColumnInfo ci) {
        model.removeColumn(ci);
    }

    public void setData(List<ColumnInfo> columns, List<T> data) {
        this.columns = columns;
        this.data = data;
        setData();
    }

    public void setData(List<T> data) {
        this.data = data;
        setData();
    }

    public void setData() {
        this.model = new DynamicTableModel<>(this.columns, this.data);
        setModel(this.model);
    }

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public DynamicTableModel<T> getModel() {
        return model;
    }

    public TableColumn getColumn(ColumnInfo ci) {
        int index = model.getColumnIndex(ci);
        if(index == -1) return null;
        return getColumn(index);
    }
}
