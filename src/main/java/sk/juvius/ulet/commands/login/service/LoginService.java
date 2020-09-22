package sk.juvius.ulet.commands.login.service;

import com.password4j.Password;
import com.password4j.SecureString;
import sk.juvius.ulet.commands.login.LoginCache;
import sk.juvius.ulet.commands.login.model.User;

import java.io.IOException;

public class LoginService {

    private final UserService userService;
    private final LoginCache cache;
    private boolean verified = false;
    private String username;

    public LoginService(UserService userService, LoginCache cache) {
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
                    verify(username, user.getHash(), cache.getUserPassword(username));
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
            e.printStackTrace();
        }
    }

    public void setLastLoggedIn(String username) {
        cache.setLastLoggedIn(username);
    }

    public boolean verify(String username, String hash, char[] password) {
        SecureString sec = new SecureString(password);
        verified = Password.check(sec, hash).withSCrypt();
        sec.clear();
        this.username = username;
        return verified;
    }

    public void unverify() {
        verified = false;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getUsername() {
        return username;
    }
}
