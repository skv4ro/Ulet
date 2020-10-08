package sk.juvius.ulet.service;

import sk.juvius.ulet.service.impl.LoginCacheServiceImpl;
import sk.juvius.ulet.model.User;

public interface LoginService {
    User getLoggedUser();
    void autoLogin();
    boolean isVerified();
    User getUserByName(String userName);
    boolean verify(User user, char[] password);
    void setLastLoggedIn(String username);
    void cache(String username, char[] password, boolean autoLogin);
    void removeFromCache(String username);
    LoginCacheService getCache();
    void unVerify();
}
