package sk.juvius.ulet.commands.login.controler;

import sk.juvius.ulet.commands.login.LoginCache;
import sk.juvius.ulet.commands.login.view.LoginView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UsernameController implements FocusListener {

    private final LoginCache cache;
    private final LoginView loginView;

    public UsernameController(LoginView loginView, LoginCache cache) {
        this.loginView = loginView;
        this.cache = cache;
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        String username = loginView.getUserName();
        if(username == null || username.isEmpty()) {
            return;
        }
        username = username.trim();
        if(cache.hasUser(username)) {
            loginView.setPassword(cache.getUserPassword(username));
            loginView.setRememberLogin(true);
            loginView.setAutoLoginEnabled(true);
            if(cache.getAutoLogin(username)) {
                loginView.setAutoLogin(true);
            }
        }
    }
}
