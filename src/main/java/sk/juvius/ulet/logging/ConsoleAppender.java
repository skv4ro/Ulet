package sk.juvius.ulet.logging;

public class ConsoleAppender implements Appender {
    @Override
    public void append(String formattedMsg) {
        System.out.println(formattedMsg);
    }
}
