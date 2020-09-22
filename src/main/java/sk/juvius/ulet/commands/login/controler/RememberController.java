package sk.juvius.ulet.commands.login.controler;

import sk.juvius.ulet.commands.login.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RememberController implements ActionListener {

    private final LoginView loginView;

    public RememberController(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loginView.setAutoLoginEnabled(loginView.getRememberPassword());
    }
}
