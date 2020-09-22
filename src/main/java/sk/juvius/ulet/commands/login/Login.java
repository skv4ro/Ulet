package sk.juvius.ulet.commands.login;

import com.ptc.cipjava.jxthrowable;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.commands.AbstractCommand;
import sk.juvius.ulet.commands.login.controler.LogoutController;
import sk.juvius.ulet.commands.login.controler.RememberController;
import sk.juvius.ulet.commands.login.controler.UsernameController;
import sk.juvius.ulet.commands.login.service.LoginService;
import sk.juvius.ulet.commands.login.view.LogoutView;
import sk.juvius.ulet.commands.login.controler.LoginController;
import sk.juvius.ulet.commands.login.view.LoginView;

public class Login extends AbstractCommand {

    private final LoginView loginView = new LoginView();;
    private final LogoutView logoutView = new LogoutView();
    private final LoginService loginService;

    public Login(AppContext appContext) {
        super(appContext);
        loginService = appContext.getLoginService();
        LoginController loginController = new LoginController(loginView, loginService);
        LogoutController logoutController = new LogoutController(loginView, logoutView, loginService);
        RememberController rememberController = new RememberController(loginView);
        UsernameController usernameController = new UsernameController(loginView, appContext.getLoginCache());
        loginView.setUsernameTextFieldController(usernameController);
        loginView.setLoginController(loginController);
        loginView.setRememberController(rememberController);
        logoutView.setLogoutController(logoutController);
    }

    @Override
    public void OnCommand() throws jxthrowable {
        if(loginService.isVerified()) {
            logoutView.setUserLabelText(loginService.getUsername());
            logoutView.setVisible(true);
        } else {
            loginView.setVisible(true);
        }
        appContext.getUtils().infoMsg("User is " + loginService.isVerified());
    }
}
