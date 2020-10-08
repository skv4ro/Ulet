package sk.juvius.ulet.service;

import sk.juvius.ulet.model.Repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryModelService {
    List<Repository> getRepositoriesByUserId(int userId) throws SQLException;
    Repository getRepoById(int id);
}
