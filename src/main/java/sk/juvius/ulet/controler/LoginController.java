package sk.juvius.ulet.controler;

import sk.juvius.ulet.service.LoginCacheService;
import sk.juvius.ulet.service.impl.LoginCacheServiceImpl;
import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.model.User;
import sk.juvius.ulet.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

public class LoginController {

    private final LoginView loginView;
    private final LoginService loginService;

    public LoginController(LoginView loginView, LoginService loginService) {
        this.loginView = loginView;
        this.loginService = loginService;
        loginView.addLoginListener(new LoginListener());
        loginView.addRememberListener(new RememberListener());
        loginView.addUsernameListener(new UsernameListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = loginView.getUserName();
            if(userName == null || userName.isEmpty()) {
                loginView.setPasswordVerified(false);
                return;
            }
            User user = loginService.getUserByName(userName.trim());
            if(user == null) {
                loginView.setPasswordVerified(false);
                return;
            }
            char[] pass = loginView.getPassword();
            boolean verified = loginService.verify(user, pass);
            loginView.setPassword(new char[0]);
            if(verified) {
                String username = user.getName();
                loginView.setPasswordVerified(true);
                if(loginView.getRememberPassword()) {
                    loginService.setLastLoggedIn(username);
                    loginService.cache(username, pass, loginView.getAutoLogin());
                } else {
                    loginService.setLastLoggedIn(null);
                    loginService.removeFromCache(username);
                }
                pass = null;
                loginView.setVisible(false);
            } else {
                loginView.setPasswordVerified(false);
                pass = null;
            }
        }
    }

    class RememberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.setAutoLoginEnabled(loginView.getRememberPassword());
        }
    }

    class UsernameListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            String username = loginView.getUserName();
            if(username == null || username.isEmpty()) {
                return;
            }
            LoginCacheService cache = loginService.getCache();
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
}
