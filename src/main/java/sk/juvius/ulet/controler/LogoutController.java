package sk.juvius.ulet.controler;

import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.view.LogoutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutController {

    private final LogoutView logoutView;
    private final LoginService loginService;

    public LogoutController(LogoutView logoutView, LoginService loginService) {
        this.logoutView = logoutView;
        this.loginService = loginService;
        logoutView.setYesListener(new LogoutListener());
    }

    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginService.unVerify();
            logoutView.setUserLabelText("");
        }
    }
}
