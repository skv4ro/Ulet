package sk.juvius.ulet.ui;

import java.awt.*;

public final class UIUtils {

    public static void moveToMousePointer(Container container) {
        moveToMousePointer(container, 0.5f, 0.5f);
    }

    public static void moveToMousePointer(Container container, float xOffset, float yOffset) {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        int screenX = screenDim.width;
        int screenY = screenDim.height;
        int mouseX = mousePoint.x;
        int mouseY = mousePoint.y;
        int width = container.getWidth();
        int height = container.getHeight();
        int x, y;
        int boundOffset = 50;

        if(mouseX < width / 2) x = boundOffset;
        else if(mouseX >= screenX - width) x = screenX - width - boundOffset;
        else x = mouseX - (int) (width * xOffset);
        if(mouseY < height / 2) y = boundOffset;
        else if(mouseY >= screenY - height) y = screenY - height - boundOffset;
        else y = mouseY - (int) (height *yOffset);
        container.setLocation(x, y);
    }
}
