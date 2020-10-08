package sk.juvius.ulet.commands;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcCommand.UICommand;
import com.ptc.pfc.pfcSession.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.util.Utilities;

public abstract class AbstractCommand extends DefaultUICommandActionListener implements Command {
    protected final AppContext appContext;
    protected final Session session;
    private final Logger log = LoggerFactory.getLogger(AbstractCommand.class);
    private final boolean designated;
    private final String name;
    private UICommand systemCmd;

    public AbstractCommand(AppContext appContext) {
        this(appContext, true);
    }

    public AbstractCommand(AppContext appContext, boolean designate) {
        this.designated = designate;
        this.appContext = appContext;
        this.session = appContext.getSession();
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
        String cmdPrefix = AppContext.APP_NAME;
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
            systemCmd = session.UICreateCommand(getCmdName(), this);
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

//TODO dat prec session z commandov lebo tu uz je pridelene session
