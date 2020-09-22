package sk.juvius.ulet.commands.login.controler;

import sk.juvius.ulet.commands.login.service.LoginService;
import sk.juvius.ulet.commands.login.view.LoginView;
import sk.juvius.ulet.commands.login.view.LogoutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutController implements ActionListener {

    private final LoginView loginView;
    private final LogoutView logoutView;
    private final LoginService loginService;

    public LogoutController(LoginView loginView, LogoutView logoutView, LoginService loginService) {
        this.loginView = loginView;
        this.logoutView = logoutView;
        this.loginService = loginService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loginService.unVerify();
        logoutView.setUserLabelText("");
        loginView.reset();
        logoutView.setVisible(false);
        loginView.setVisible(true);
    }
}
