package sk.juvius.ulet.commands;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcCommand.UICommand;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.logging.Logger;
import sk.juvius.ulet.util.Utilities;

public abstract class AbstractCommand extends DefaultUICommandActionListener implements Command {
    protected final AppContext appContext;
    private final Logger log = AppContext.getLogger();
    private final boolean designated;
    private final String name;
    private UICommand systemCmd;

    public AbstractCommand(AppContext appContext) {
        this(appContext, true);
    }

    public AbstractCommand(AppContext appContext, boolean designate) {
        this.designated = designate;
        this.appContext = appContext;
        name = getClass().getSimpleName();
    }

    @Override
    public UICommand getSystemCmd() {
        return systemCmd;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDesignated() {
        return designated;
    }

    @Override
    public String getCmdName() {
        String cmdPrefix = "Avsu";
        return cmdPrefix + getName();
    }

    private String getLabel() {
        String labelSuffix = ".label";
        return getName() + labelSuffix;
    }

    private String getHelp() {
        String helpSuffix = ".help";
        return getName() + helpSuffix;
    }

    private String getIcon() {
        String iconExt = ".png";
        return getName() + iconExt;
    }

    @Override
    public void build(String msgFile) {
        try {
            systemCmd = appContext.getSession().UICreateCommand(getCmdName(), this);
            log.info("Command " + getCmdName() + " is built");
            if(designated) {
                systemCmd.SetIcon(getIcon());
                systemCmd.Designate(msgFile, getLabel(), getHelp(), null);
                log.info("Command " + getCmdName() + " is designated");
            }
        } catch (jxthrowable e) {
            log.error(getCmdName() + " " + Utilities.stackTraceToString(e));
        }
    }
}
