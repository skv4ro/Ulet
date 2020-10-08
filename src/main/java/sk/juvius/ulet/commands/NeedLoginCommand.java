package sk.juvius.ulet.commands;

import com.ptc.cipjava.jxthrowable;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.commands.login.LoginCmd;

public abstract class NeedLoginCommand extends PrivilegedCommand {

    public NeedLoginCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    protected boolean privileged() throws jxthrowable {
        if (!isLoggedIn()) {
            appContext.getAppBuilder().getCommand(LoginCmd.class).run();
        }
        return isLoggedIn();
    }

    private boolean isLoggedIn() {
        return appContext.services.getLoginService().isVerified();
    }
}
