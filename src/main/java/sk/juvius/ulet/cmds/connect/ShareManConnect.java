package sk.juvius.ulet.cmds.connect;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

public class ShareManConnect extends DefaultUICommandActionListener {

    public static Dialog DIALOG;

    public ShareManConnect() {
        ShareManConnect.DIALOG = new Dialog();
    }

    @Override
    public void OnCommand() throws jxthrowable {
        DIALOG.show();
    }
}
