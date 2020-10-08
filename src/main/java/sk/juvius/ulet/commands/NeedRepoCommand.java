package sk.juvius.ulet.commands;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.commands.repository.RepositoryCmd;

public abstract class NeedRepoCommand extends NeedLoginCommand {

    protected Model curModel;

    public NeedRepoCommand(AppContext appContext) {
        super(appContext);
    }

    protected boolean privileged() throws jxthrowable {
        if(!super.privileged()) return false;
        if(!isRepoSelected()) {
            appContext.getAppBuilder().getCommand(RepositoryCmd.class).run();
        }
        return isRepoSelected();
    }

    private boolean isRepoSelected() {
        return appContext.services.getRepositoryService().getCurrentRepo() != null;
    }
}
