package sk.juvius.ulet.service;

import sk.juvius.ulet.model.Repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryService {
    Repository getCurrentRepo();
    void setCurrentRepo(Repository repository, boolean cache);
    List<Repository> getAllUserRepositories() throws SQLException;
    List<Repository> getCachedRepositories();
    String getCachedRepo();
    void autoSelectRepo();
}
