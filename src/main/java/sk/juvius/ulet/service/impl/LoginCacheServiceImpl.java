package sk.juvius.ulet.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.core.cache.AbstractCache;
import sk.juvius.ulet.service.LoginCacheService;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class LoginCacheServiceImpl extends AbstractCache implements LoginCacheService {

    private static final Logger log = LoggerFactory.getLogger(LoginCacheServiceImpl.class);
    private final Map<String, String[]> records = new HashMap<>();
    private final String itemSeparator = "\t";
    private final String rowSeparator = "\n";
    private String lastLoggedIn;

    public LoginCacheServiceImpl(File cacheFile) {
        super(cacheFile, log);
    }

    @Override
    protected void loadContent() throws IOException {
        super.loadContent();
        String[] rows = content.split(rowSeparator);
        for(int i = 0; i < rows.length; i++) {
            String row = rows[i];
            String[] items = row.split(itemSeparator);
            if(i == 0 && items.length == 1) {
                lastLoggedIn = row;
            } else {
                records.put(items[0], items);
            }
        }
    }

    @Override
    public void write() throws IOException {
        StringBuilder builder = new StringBuilder();
        if(lastLoggedIn != null) builder.append(lastLoggedIn).append(rowSeparator);
        for (String[] items : records.values()) {
            builder.append(items[0])
                    .append(itemSeparator)
                    .append(items[1])
                    .append(itemSeparator)
                    .append(items[2])
                    .append(rowSeparator);
        }
        if(builder.toString().isEmpty()) {
            if(exist()) delete();
            return;
        }
        builder.deleteCharAt(builder.length() - 1);
        content = builder.toString();
        super.write();
    }

    @Override
    public void setLastLoggedIn(String lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Override
    public void createRecord(String username, char[] password, boolean autoLogin) {
        records.put(username, new String[] {username, new String(password), Boolean.toString(autoLogin)});
    }

    @Override
    public void removeRecord(String username) {
        records.remove(username);
    }

    @Override
    public boolean hasUser(String userName) {
        return records.containsKey(userName);
    }

    @Override
    public char[] getUserPassword(String username) {
        String[] record = records.get(username);
        if(record == null) return null;
        return record[1].toCharArray();
    }

    @Override
    public boolean getAutoLogin(String username) {
        String[] arr = records.get(username);
        if(arr == null) return false;
        return Boolean.parseBoolean(arr[2]);
    }

    @Override
    public String getLastLoggedIn() {
        return lastLoggedIn;
    }

    @Override
    public List<String[]> getAllRecords() {
        return new ArrayList<>(records.values());
    }
}
