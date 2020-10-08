package sk.juvius.ulet.context;

import sk.juvius.ulet.service.*;
import sk.juvius.ulet.service.impl.*;
import sk.juvius.ulet.db.CRUD;

import java.io.File;

public final class ServicesContext {

    private final File loginCacheFile;

    private final LoginService loginService;
    private final UserService userService;
    private final RepositoryModelService repositoryModelService;
    private final LoginCacheService loginCacheService;
    private final RepositoryCacheService repositoryCacheService;
    private final RepositoryService repositoryService;

    public ServicesContext(CRUD crud, File appData, MessageManager msgManager) {
        this.userService = new UserServiceImpl(crud);
        this.repositoryModelService = new RepositoryModelServiceImpl(crud);
        this.loginCacheFile = new File(appData, "login_data");
        this.loginCacheService = new LoginCacheServiceImpl(loginCacheFile);
        this.loginService = new LoginServiceImpl(userService, loginCacheService);
        this.repositoryCacheService = new RepositoryCacheServiceImpl(appData, loginService);
        this.repositoryService = new RepositoryServiceImpl(
                repositoryModelService, repositoryCacheService, loginService, msgManager);
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public UserService getUserService() {
        return userService;
    }

    public RepositoryModelService getRepositoryModelService() {
        return repositoryModelService;
    }

    public File getLoginCacheFile() {
        return loginCacheFile;
    }

    public LoginCacheService getLoginCacheService() {
        return loginCacheService;
    }

    public RepositoryCacheService getRepositoryCacheService() {
        return repositoryCacheService;
    }

    public RepositoryService getRepositoryService() {
        return repositoryService;
    }
}
