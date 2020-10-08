package sk.juvius.ulet.commands.repository;

import com.ptc.cipjava.jxthrowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.commands.AbstractCommand;
import sk.juvius.ulet.commands.NeedLoginCommand;
import sk.juvius.ulet.commands.login.LoginCmd;
import sk.juvius.ulet.controler.RepositoryController;
import sk.juvius.ulet.service.RepositoryService;
import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.util.Utilities;
import sk.juvius.ulet.view.RepositoryView;

import java.sql.SQLException;

public class RepositoryCmd extends NeedLoginCommand {

    private static final Logger log = LoggerFactory.getLogger(RepositoryCmd.class);

    private final LoginService loginService;
    private final RepositoryView repositoryView;
    private final RepositoryService repositoryService;
    private final RepositoryController repositoryController;

    public RepositoryCmd(AppContext appContext) {
        super(appContext);
        this.loginService = appContext.services.getLoginService();
        this.repositoryView = new RepositoryView();
        this.repositoryService = appContext.services.getRepositoryService();
        this.repositoryController = new RepositoryController(repositoryView, appContext.services.getRepositoryService());
    }

    @Override
    public void OnCommand() throws jxthrowable {
        try {
            run();
        } catch (Exception e) {
            log.debug(Utilities.stackTraceToString(e));
        }
    }

    @Override
    public void run() throws jxthrowable {
        if(!privileged()) return;
        try {
            repositoryView.setRepoList(repositoryService.getAllUserRepositories());
            repositoryController.search(repositoryService.getCachedRepo());
            repositoryView.setUserLabel(repositoryService.getCachedRepositories().size()
                    , loginService.getLoggedUser().getName());
            repositoryView.setVisible(true);
        } catch (SQLException e) {
            log.error("Cannot read repositories due to: " + e.getMessage());
        }
    }
}
