package sk.juvius.ulet.view;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;
import sk.juvius.ulet.ui.YesNoDialog;

public class LogoutView extends YesNoDialog {

    private String userLabel;

    public LogoutView(Session session) throws jxthrowable {
        super(session, "Logout");
    }

    public void setUserLabelText(String text) {
        if(text.isEmpty()) {
            userLabel = null;
        }
        this.userLabel = text;
    }

    @Override
    public void setVisible(boolean visible) throws jxthrowable {
        if(!visible) return;
        if(userLabel == null) {
            setMessage("You are not logged in.");
        } else {
            setMessage("You are logged in as: " + userLabel + "\nDo you want to log out?");
        }
        super.show();
    }
}
