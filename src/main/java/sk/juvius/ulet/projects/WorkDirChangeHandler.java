package sk.juvius.ulet.projects;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandBracketListener;
import sk.juvius.ulet.c;

public class WorkDirChangeHandler extends DefaultUICommandBracketListener {

    @Override
    public void OnAfterCommand() throws jxthrowable {
        c.curProject = null;
    }
}
