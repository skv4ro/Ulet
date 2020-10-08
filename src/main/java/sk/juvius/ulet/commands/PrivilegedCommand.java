package sk.juvius.ulet.commands;

import com.ptc.cipjava.jxthrowable;
import sk.juvius.ulet.AppContext;

public abstract class PrivilegedCommand extends AbstractCommand {

    public PrivilegedCommand(AppContext appContext) {
        super(appContext);
    }

    protected abstract boolean privileged() throws jxthrowable;
}
