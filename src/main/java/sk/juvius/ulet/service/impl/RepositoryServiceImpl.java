package sk.juvius.ulet.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.context.MessageManager;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.service.RepositoryCacheService;
import sk.juvius.ulet.service.RepositoryModelService;
import sk.juvius.ulet.service.RepositoryService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RepositoryServiceImpl implements RepositoryService {

    private final static Logger log = LoggerFactory.getLogger(RepositoryServiceImpl.class);

    private final RepositoryModelService repositoryModelService;
    private final RepositoryCacheService repositoryCacheService;
    private final LoginService loginService;
    private final MessageManager msgManager;
    private List<Repository> cachedRepositories;
    private Repository repository;

    public RepositoryServiceImpl(RepositoryModelService repositoryModelService,
                                 RepositoryCacheService repositoryCacheService,
                                 LoginService loginService,
                                 MessageManager msgManager) {
        this.repositoryModelService = repositoryModelService;
        this.repositoryCacheService = repositoryCacheService;
        this.loginService = loginService;
        this.msgManager = msgManager;
    }

    @Override
    public Repository getCurrentRepo() {
        return repository;
    }

    @Override
    public void setCurrentRepo(Repository repository, boolean cache) {
        this.repository = repository;
        repositoryCacheService.setContent(Integer.toString(repository.getId()));
        if(cache) {
            try {
                repositoryCacheService.write();
            } catch (IOException e) {
                log.warn("Cannot write repository cache: " + e.getMessage());
            }
        } else {
            if(repositoryCacheService.exist()) {
                try {
                    repositoryCacheService.delete();
                } catch (IOException e) {
                    log.warn("Cannot delete repo cache file: " + e.getMessage());
                }
            }
        }
        msgManager.displayRepoSelectedMsg(repository.getDisplayName());
    }

    @Override
    public List<Repository> getAllUserRepositories() throws SQLException {
        int id = loginService.getLoggedUser().getId();
        cachedRepositories = repositoryModelService.getRepositoriesByUserId(id);
        return cachedRepositories;
    }

    @Override
    public List<Repository> getCachedRepositories() {
        return cachedRepositories;
    }

    @Override
    public String getCachedRepo() {
        repositoryCacheService.load();
        String repo = repositoryCacheService.getContent();
        return repo == null ? "" : repo;
    }

    @Override
    public void autoSelectRepo() {
        if(repositoryCacheService.exist()) {
            repositoryCacheService.load();
            int id = repositoryCacheService.getRepoId();
            setCurrentRepo(repositoryModelService.getRepoById(id), true);
        }
    }
}
