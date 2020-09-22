package sk.juvius.ulet.logging;

public enum Level {
    ALL(0),
    DEBUG(10),
    TRACE(20),
    INFO (30),
    WARN(40),
    ERROR(50),
    FATAL(60),
    OFF(100);

    private final int value;

    Level(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
