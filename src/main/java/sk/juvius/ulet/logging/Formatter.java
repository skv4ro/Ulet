package sk.juvius.ulet.logging;

public interface Formatter {
    String format(String unformattedMsg, Level level);
}
