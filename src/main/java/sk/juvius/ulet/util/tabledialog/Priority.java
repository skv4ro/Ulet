package sk.juvius.ulet.util.tabledialog;

import javafx.scene.paint.Color;

public enum Priority {
    NO(0),
    OK(1),
    WARNING(2),
    SEVERE(3),
    FATAL(4),
    OFF(5)
            ;

    private final int priority;

    Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public Color getColor() {
        switch (priority) {
            case 0:
                return Color.valueOf("#3498DB");
            case 1:
                return Color.valueOf("#2ECC40");
            case 2:
                return Color.valueOf("#FFDC00");
            case 3:
                return Color.valueOf("#FF851B");
            case 4:
                return Color.valueOf("#FF4136");
            default:
                return Color.WHITE;
        }
    }
}
