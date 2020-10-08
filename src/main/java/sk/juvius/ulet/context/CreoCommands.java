package sk.juvius.ulet.context;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.UICommand;
import com.ptc.pfc.pfcSession.Session;

public class CreoCommands {

    public static final String SKETCH = "ProCmdDatumSketCurve";
    public static final String HOLE = "ProCmdHole";
    private final Session session;

    public CreoCommands(Session session) {
        this.session = session;
    }

    private UICommand getSystemCmd(String strCmd) throws jxthrowable {
        return session.UIGetCommand(strCmd);
    }

    public UICommand getSketchCmd() throws jxthrowable {
        return getSystemCmd(SKETCH);
    }

    public UICommand getHoleCmd() throws jxthrowable {
        return getSystemCmd(HOLE);
    }
}
