package sk.juvius.ulet.service;

import sk.juvius.ulet.core.cache.CacheFile;

import java.util.List;

public interface LoginCacheService extends CacheFile {
    void setLastLoggedIn(String lastLoggedIn);
    void createRecord(String username, char[] password, boolean autoLogin);
    void removeRecord(String username);
    boolean hasUser(String userName);
    char[] getUserPassword(String username);
    boolean getAutoLogin(String username);
    String getLastLoggedIn();
    List<String[]> getAllRecords();
}
