package sk.juvius.ulet.view;

import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.R;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.ui.Dialog;
import sk.juvius.ulet.ui.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class RepositoryView extends Dialog {

    private final JTextField searchTextField = new JTextField();
    private final JList<Repository> repoList = new JList<>();
    private final JLabel messageLabel = new JLabel("no repository selected");
    private final JLabel reposLabel = new JLabel("repositories");
    private final JCheckBox rememberRepoCheckBox = new JCheckBox("remember repository");
    private final JButton selectButton = new JButton("select");
    private final Color repoSelectedColor =  new Color(0,0,0, 0);
    private final Color noRepoColor = Color.red;

    public RepositoryView() {
        setTitle(AppContext.APP_NAME + " repositories");
        setIconImage(R.getImage("repository.png"));
        Panel content = new Panel();

        JLabel searchLabel = new JLabel("search");
        setMinimumSize(new Dimension(128, 256));

        messageLabel.setForeground(repoSelectedColor);
        repoList.setPreferredSize(new Dimension(repoList.getWidth(), 160));
        repoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reposLabel.setBorder(new EmptyBorder(5,0,5,0));

        GridBagConstraints gbc = new GridBagConstraints();
        int spacing = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(spacing,spacing,spacing,spacing);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        content.add(searchLabel, gbc);
        gbc.gridy++;
        content.add(searchTextField, gbc);
        gbc.gridy++;
        content.add(reposLabel, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        content.add(repoList, gbc);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy++;
        content.add(rememberRepoCheckBox, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(messageLabel, gbc);
        gbc.gridy++;
        content.add(selectButton, gbc);

        add(content);
        Dimension dim = content.getPreferredSize();
        dim.width = 416;
        content.setPreferredSize(dim);
        pack();
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(selectButton);
    }

    public boolean isRememberRepo() {
        return rememberRepoCheckBox.isSelected();
    }

    public void setUserLabel(int numOfRepos, String username) {
        reposLabel.setText("<html>" + numOfRepos + " repositories of <b> " + username +"</b></html>");
    }

    public void addSearchKeyListener(KeyListener listener) {
        searchTextField.addKeyListener(listener);
    }

    public void addSelectListener(ActionListener listener) {
        this.selectButton.addActionListener(listener);
    }

    public void setSearchText(String text) {
        searchTextField.setText(text);
    }

    public String getSearchText() {
        return searchTextField.getText();
    }

    public JList<Repository> getRepoList() {
        return repoList;
    }

    public void setRepoList(java.util.List<Repository> list) {
        DefaultListModel<Repository> model = new DefaultListModel<>();
        for(Repository repo : list) {
            model.addElement(repo);
        }
        repoList.setModel(model);
    }

    public void setSelectedRepo(boolean selected) {
        if(selected) {
            messageLabel.setForeground(repoSelectedColor);
        } else {
            messageLabel.setForeground(noRepoColor);
        }
    }
}
