package sk.juvius.ulet.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration extends Properties {

    private static final Level DEF_LEVEL = Level.OFF;
    private static final Formatter DEF_FORMATTER = new PatternFormatter(null);
    private static final Appender DEF_APPENDER = new ConsoleAppender();
    private Level level = DEF_LEVEL;
    private Appender appender = DEF_APPENDER;
    private Formatter formatter = DEF_FORMATTER;

    public Configuration(File file) {
        if(file == null || !file.exists()) return;
        try(FileInputStream fis = new FileInputStream(file)) {
            load(fis);
            level = resolveLevel(getSafe("level"));
            appender = resolveAppender(getSafe("appender"));
            formatter = resolveFormatter(getSafe("formatter"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Level getLevel() {
        return level;
    }

    public Appender getAppender() {
        return appender;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    private String getSafe(String key) {
        Object val = get(key);
        return val == null ? null : val.toString();
    }

    private Level resolveLevel(String strLevel) {
        if(strLevel == null) return DEF_LEVEL;
        try {
            return Level.valueOf(strLevel);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong level. Using WARN Level...");
            return DEF_LEVEL;
        }
    }

    private Appender resolveAppender(String appender) {
        if(appender == null) {
            System.out.println("No appender specified. Using ConsoleAppender...");
            return new ConsoleAppender();
        }
        appender = appender.toLowerCase();
        if(appender.equals("console")) return new ConsoleAppender();
        else if(appender.equals("file")) {
            String path = getSafe("path");
            File file = path == null ? null : (path.isEmpty() ? null : new File(path));
            return new FileAppender(file);
        } else {
            System.out.println("No appender type specified. Using Console Appender...");
            return DEF_APPENDER;
        }
    }

    private Formatter resolveFormatter(String formatter) {
        if(formatter == null) {
            System.out.println("No formatter specified. Using PatternFormatter with default pattern...");
            return DEF_FORMATTER;
        }
        formatter = formatter.toLowerCase();
        if(formatter.equals("pattern")) {
            String pattern = getSafe("pattern");
            if(pattern == null) {
                System.out.println("Pattern not specified. Using default pattern...");
            }
            return new PatternFormatter(pattern);
        } else {
            System.out.println("No formatter specified. Using PatternFormatter with default pattern...");
            return DEF_FORMATTER;
        }
    }
}
