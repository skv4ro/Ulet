package sk.juvius.ulet.ui.table;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CellBorder extends EmptyBorder {
    public final boolean headerCell;
    private final Color gridColor;
    public boolean isStartCell = false;
    public CellBorder(int top, int left, int bottom, int right, Color gridColor, boolean headerCell) {
        super(top, left, bottom, right);
        this.gridColor = gridColor;
        this.headerCell = headerCell;
    }
    public CellBorder(Insets insets, Color gridColor, boolean headerCell) {
        super(insets);
        this.gridColor = gridColor;
        this.headerCell = headerCell;
    }
    @Override public boolean isBorderOpaque() {
        return true;
    }
    @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.translate(x, y);
        g2.setPaint(gridColor);
        if (!isStartCell) {
            g2.drawLine(0, 0, 0, h - 1);
        }
        if(headerCell) {
            g2.drawLine(0,h - 1,w - 1, h - 1);
        }
        g2.dispose();
    }
}
