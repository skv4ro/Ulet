package sk.juvius.ulet;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.UICommand;
import com.ptc.pfc.pfcCommand.UICommandActionListener;
import com.ptc.pfc.pfcSession.Session;

public abstract class AbstractBuilder {

    protected final Session session;

    public AbstractBuilder(Session session) {
        this.session = session;
    }

    public abstract void build() throws jxthrowable;

    protected void buildCommand (
            UICommandActionListener listener,
            String name,
            String icon,
            String label,
            String help,
            String help2) {
        try {
            UICommand cmd = session.UICreateCommand(name, listener);
            cmd.SetIcon(icon);
            cmd.Designate(c.MSG_CMD, label, help, help2);
        } catch (Exception e) {
            c.debugMsg(e);
        }
    }
}
