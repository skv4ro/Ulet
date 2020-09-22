package sk.juvius.ulet.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PatternFormatter implements Formatter {

    private static final String TOKEN_PREFIX = "%";
    private static final String DATE_TIME_TOKEN = TOKEN_PREFIX + "dt";
    private static final String LEVEL_TOKEN = TOKEN_PREFIX + "l";
    private static final String MSG_TOKEN = TOKEN_PREFIX + "msg";
    private static final String SPACE_TOKEN = TOKEN_PREFIX + "_";
    private static final String DEF_PATTERN = assignDefPattern();
    private static final Set<String> ALL_TOKENS = new HashSet<>();
    private final String[] patternArray;
    private final StringBuilder builder = new StringBuilder();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
    private Level level;
    private String message;

    static {
        ALL_TOKENS.add(DATE_TIME_TOKEN);
        ALL_TOKENS.add(LEVEL_TOKEN);
        ALL_TOKENS.add(MSG_TOKEN);
        ALL_TOKENS.add(SPACE_TOKEN);
    }

    private static String assignDefPattern() {
        return insertSpaces(DATE_TIME_TOKEN, SPACE_TOKEN, LEVEL_TOKEN, SPACE_TOKEN, MSG_TOKEN);
    }

    private static String insertSpaces(String... strings) {
        StringBuilder builder = new StringBuilder();
        for(String s : strings) {
            builder.append(s).append(" ");
        }
        String result = builder.toString();
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public PatternFormatter(String pattern) {
        if(pattern == null || pattern.trim().isEmpty()) pattern = DEF_PATTERN;
        pattern = resolveVariables(pattern);
        patternArray = pattern.split(" ");
    }

    @Override
    public String format(String unformattedMsg, Level level) {
        builder.setLength(0);
        this.level = level;
        this.message = unformattedMsg;
        for(String s : patternArray) {
            if(ALL_TOKENS.contains(s.trim())) builder.append(evalToken(s));
            else builder.append(s);
        }
        return builder.toString();
    }

    private String resolveVariables(String pattern) {
        if(pattern.contains(DATE_TIME_TOKEN + "{")) {
            String formatterString = getDateTimeFormatterString(pattern);
            pattern = pattern.replace("{" + formatterString + "}", "");
            dateTimeFormatter = DateTimeFormatter.ofPattern(formatterString);
        }
        return pattern;
    }

    private String getDateTimeFormatterString(String pattern) {
        int index = pattern.indexOf(DATE_TIME_TOKEN);
        return pattern.substring(index + DATE_TIME_TOKEN.length() + 1, pattern.lastIndexOf("}"));
    }

    private String evalToken(String token) {
        if(token.contains(LEVEL_TOKEN)) return level.toString();
        else if(token.contains(DATE_TIME_TOKEN)) return getCurrentDateTime(dateTimeFormatter);
        else if(token.contains(MSG_TOKEN)) return message;
        else if(token.contains(SPACE_TOKEN)) return  " ";
        else return "?";
    }

    private String getCurrentDateTime(DateTimeFormatter formatter) {
        return LocalDateTime.now().format(formatter);
    }
}
