package sk.juvius.ulet.commands.login.controler;

import sk.juvius.ulet.commands.login.service.LoginService;
import sk.juvius.ulet.commands.login.model.User;
import sk.juvius.ulet.commands.login.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private final LoginView loginView;
    private final LoginService loginService;

    public LoginController(LoginView loginView, LoginService loginService) {
        this.loginView = loginView;
        this.loginService = loginService;
    }

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
