package sk.juvius.ulet.controler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.service.RepositoryService;
import sk.juvius.ulet.view.RepositoryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class RepositoryController {

    private final Logger log = LoggerFactory.getLogger(RepositoryController.class);

    private final RepositoryView repositoryView;
    private final RepositoryService repositoryService;

    public RepositoryController(RepositoryView repositoryView, RepositoryService repositoryService) {
        this.repositoryView = repositoryView;
        this.repositoryService = repositoryService;
        repositoryView.addSearchKeyListener(new SearchListener());
        repositoryView.addSelectListener(new SelectListener());
    }

    class SearchListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            String searchText = repositoryView.getSearchText().trim().toLowerCase();
            filter(searchText);
        }
    }

    class SelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Repository selectedRepo = repositoryView.getRepoList().getSelectedValue();
            if(selectedRepo == null) {
                repositoryView.setSelectedRepo(false);
            } else {
                repositoryService.setCurrentRepo(selectedRepo, repositoryView.isRememberRepo());
                repositoryView.setSelectedRepo(true);
                repositoryView.setVisible(false);
            }
        }
    }

    public void search(String text) {
        repositoryView.setSearchText(text);
        filter(text);
    }

    private void filter(String searchText) {
        searchText = StringUtils.stripAccents(searchText);
        searchText = searchText.toLowerCase();
        String[] tokens = searchText.split(" ");
        DefaultListModel<Repository> listModel = new DefaultListModel<>();
        List<Repository> repos = repositoryService.getCachedRepositories();
        for(Repository repo : repos) {
            String displayName = repo.getDisplayName();
            displayName = StringUtils.stripAccents(displayName);
            displayName = displayName.toLowerCase();
            int i = 0;
            for(String token : tokens) {
                if(displayName.contains(token)) i++;
            }
            if (i == tokens.length) listModel.addElement(repo);
        }
        repositoryView.getRepoList().setModel(listModel);
    }
}
