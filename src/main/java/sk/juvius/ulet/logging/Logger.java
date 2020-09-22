package sk.juvius.ulet.logging;

import java.io.File;

public class Logger {
    private Formatter formatter = new PatternFormatter("");
    private Appender appender = new ConsoleAppender();
    private Level level = Level.OFF;

    public Logger() {}

    public Logger(Level level) {
        this.level = level;
    }

    public Logger(Configuration config) {
        formatter = config.getFormatter();
        appender = config.getAppender();
        if(appender instanceof FileAppender) {
            if(!((FileAppender) appender).isInitialized()) {
                System.out.println("Cannot initialize FileAppender. Using ConsoleAppender...");
                appender = new ConsoleAppender();
            }
        }
        level = config.getLevel();
    }

    public Logger(File file) {
        FileAppender appender = new FileAppender(file);
        if(appender.isInitialized()) this.appender = new FileAppender(file);
        else {
            System.out.println("Cannot initialize FileAppender. Using ConsoleAppender...");
        }
    }

    public Logger(File file, Level level) {
        this(file);
        this.level = level;
    }

    public Logger(File file, Level level, String pattern) {
        this(file, level);
        this.formatter = new PatternFormatter(pattern);
    }

    public void info(String msg) {
        log(msg, Level.INFO);
    }

    public void error(String msg) {
        log(msg, Level.ERROR);
    }

    public void debug(String msg) {
        log(msg, Level.DEBUG);
    }

    public void trace(String msg) {
        log(msg, Level.TRACE);
    }

    public void warn(String msg) {
        log(msg, Level.WARN);
    }

    public void fatal(String msg) {
        log(msg, Level.FATAL);
    }

    public void log(String unformattedMsg, Level level) {
        if(level.getValue() >= this.level.getValue())
            this.appender.append(this.formatter.format(unformattedMsg, level));
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public Appender getAppender() {
        return appender;
    }

    public void setAppender(Appender appender) {
        this.appender = appender;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
