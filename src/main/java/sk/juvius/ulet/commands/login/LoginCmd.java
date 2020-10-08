package sk.juvius.ulet.commands.login;

import com.ptc.cipjava.jxthrowable;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.commands.AbstractCommand;
import sk.juvius.ulet.controler.LogoutController;
import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.view.LogoutView;
import sk.juvius.ulet.controler.LoginController;
import sk.juvius.ulet.view.LoginView;

public class LoginCmd extends AbstractCommand {

    private final LoginView loginView = new LoginView();;
    private final LogoutView logoutView;
    private final LoginService loginService;

    public LoginCmd(AppContext appContext) throws jxthrowable {
        super(appContext);
        logoutView = new LogoutView(appContext.getSession());
        loginService = appContext.services.getLoginService();
        new LoginController(loginView, loginService);
        new LogoutController(logoutView, loginService);
    }

    @Override
    public void OnCommand() throws jxthrowable {
        run();
    }

    @Override
    public void run() throws jxthrowable {
        if(loginService.isVerified()) {
            logoutView.setUserLabelText(loginService.getLoggedUser().getName());
            logoutView.setVisible(true);
            if(!loginService.isVerified()) {
                loginView.reset();
                loginView.setVisible(true);
            }
        } else {
            loginView.setVisible(true);
        }
    }

    //TODO dorobit spravy po logine
    //TODO pridat autologin a login msg
}
