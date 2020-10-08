package sk.juvius.ulet.ui.table;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {

    private final CellBorder cellBorder;

    public CellRenderer(Color gridColor) {
        this.cellBorder = new CellBorder(2,5,2,5, gridColor, false);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent component = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellBorder.isStartCell = column == 0;
        component.setBorder(new CompoundBorder(cellBorder, component.getBorder()));
        return component;
    }
}
