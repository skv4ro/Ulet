package sk.juvius.ulet.service.impl;

import sk.juvius.ulet.db.CRUD;
import sk.juvius.ulet.db.RowData;
import sk.juvius.ulet.db.columns.IntegerColumn;
import sk.juvius.ulet.db.columns.StringColumn;
import sk.juvius.ulet.db.values.IntegerValue;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.service.RepositoryModelService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryModelServiceImpl implements RepositoryModelService {

    private final CRUD crud;

    private final String tableName = "repositories";
    private final IntegerColumn idCol = new IntegerColumn(tableName + ".id");
    private final StringColumn displayNameCol = new StringColumn("display_name");
    private final StringColumn pathCol = new StringColumn("path");
    private final StringColumn bakPathCol = new StringColumn("bak_path");

    public RepositoryModelServiceImpl(CRUD crud) {
        this.crud = crud;
    }

    @Override
    public List<Repository> getRepositoriesByUserId(int userId) throws SQLException {
        IntegerValue userIdVal = new IntegerValue(userId);
        List<RowData> data = crud
                .select(idCol, displayNameCol, pathCol, bakPathCol)
                .from(tableName)
                .innerJoin("users_repositories")
                .onEq("repositories.id", "users_repositories.repo_id")
                .whereEq("user_id", userIdVal)
                .orderBy(displayNameCol)
                .fetch();
        List<Repository> list = new ArrayList<>();
        for(RowData row : data) {
            list.add(createRepo(row));
        }
        return list;
    }

    @Override
    public Repository getRepoById(int id) {
        IntegerValue userIdVal = new IntegerValue(id);
        List<RowData> data = null;
        try { //TODO prerobit taky aby hadzalo vynimku a v autorepo je catchovat
            data = crud
                    .select(idCol, displayNameCol, pathCol, bakPathCol)
                    .from(tableName)
                    .innerJoin("users_repositories")
                    .onEq("repositories.id", "users_repositories.repo_id")
                    .whereEq("user_id", userIdVal)
                    .orderBy(displayNameCol)
                    .fetch();
            if(data.size() == 1) return createRepo(data.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Repository createRepo(RowData row) {
        int id = row.getInteger(idCol);
        String displayName = row.getString(displayNameCol);
        String path = row.getString(pathCol);
        String bakPath = row.getString(bakPathCol);
        return new Repository(id, displayName, path, bakPath);
    }
}
