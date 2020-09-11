package sk.juvius.ulet.cmds.connect;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcSession.Session;

public class ShareManConnect extends DefaultUICommandActionListener {

    public static Dialog DIALOG;

    public ShareManConnect(Session session) {
        ShareManConnect.DIALOG = new Dialog(session);
    }

    @Override
    public void OnCommand() throws jxthrowable {
        DIALOG.show();
    }
}
