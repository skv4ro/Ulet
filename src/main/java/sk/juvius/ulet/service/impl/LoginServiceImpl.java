package sk.juvius.ulet.service.impl;

import com.password4j.Password;
import com.password4j.SecureString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.model.User;
import sk.juvius.ulet.service.LoginCacheService;
import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.service.UserService;

import java.io.IOException;

public class LoginServiceImpl implements LoginService {

    private final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    private final UserService userService;
    private final LoginCacheService cache;
    private boolean verified = false;
    private User user;

    public LoginServiceImpl(UserService userService, LoginCacheService cache) {
        this.userService = userService;
        this.cache = cache;
    }

    public User getUserByName(String userName) {
        return userService.getUserByName(userName);
    }

    public void autoLogin() {
        cache.load();
        if(cache.exist()) {
            String username = cache.getLastLoggedIn();
            if(username != null) {
                if(cache.getAutoLogin(username)) {
                    User user = userService.getUserByName(username);
                    verify(user, cache.getUserPassword(username));
                }
            }
        }
    }

    public void cache(String username, char[] password, boolean autoLogin) {
        cache.createRecord(username, password, autoLogin);
        try {
            cache.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromCache(String username) {
        cache.removeRecord(username);
        try {
            cache.write();
        } catch (IOException e) {
            log.warn("Cannot write removed login cache record: " + e.getMessage());
        }
    }

    public void setLastLoggedIn(String username) {
        cache.setLastLoggedIn(username);
    }

    public boolean verify(User user, char[] password) {
        SecureString sec = new SecureString(password);
        verified = Password.check(sec, user.getHash()).withSCrypt();
        sec.clear();
        this.user = user;
        return verified;
    }

    public void unVerify() {
        verified = false;
    }

    public boolean isVerified() {
        return verified;
    }

    public User getLoggedUser() {
        return user;
    }

    public LoginCacheService getCache() {
        return cache;
    }
}
