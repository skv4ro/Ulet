package sk.juvius.ulet.ui.table;

import sk.juvius.ulet.R;

import javax.swing.*;
import java.awt.*;

public class ImageCellRenderer extends CellRenderer {
    public ImageCellRenderer(Color gridColor) {
        super(gridColor);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setText(null);
        if(value instanceof Image) {
            component.setIcon(new ImageIcon((Image) value));
            component.setHorizontalAlignment(CENTER);
        } else {
            component.setIcon(null);
        }
        return component;
    }
}
