package sk.juvius.ulet.ui.table.renderer;

import sk.juvius.ulet.ui.table.CellBorder;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class HeaderCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        CellBorder cellBorder = new CellBorder(5,5,7,5, table.getGridColor(), true);;
        cellBorder.isStartCell = column == 0;
        Icon sortIcon = getSortIcon(table, column);
        if(sortIcon != null) label.setIcon(sortIcon);
        if(value == null) label.setText("");
        else label.setText(value.toString());
        label.setBorder(cellBorder);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        return label;
    }

    Icon getSortIcon(JTable table, int column) {
        Icon sortIcon = null;
        SortOrder sortOrder =
                getColumnSortOrder(table, column);
        if (sortOrder != null) {
            switch (sortOrder) {
                case ASCENDING:
                    sortIcon =
                            UIManager.getIcon("Table.ascendingSortIcon");
                    break;
                case DESCENDING:
                    sortIcon =
                            UIManager.getIcon("Table.descendingSortIcon");
                    break;
            }
        }
        return sortIcon;
    }

    public static SortOrder getColumnSortOrder(JTable var0, int var1) {
        SortOrder var2 = null;
        if (var0 != null && var0.getRowSorter() != null) {
            List<? extends RowSorter.SortKey> var3 = var0.getRowSorter().getSortKeys();
            if (var3.size() > 0 && var3.get(0).getColumn() == var0.convertColumnIndexToModel(var1)) {
                var2 = var3.get(0).getSortOrder();
            }
        }
        return var2;
    }
}
