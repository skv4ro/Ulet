package sk.juvius.ulet.cmds.connect;

import com.ptc.pfc.pfcSession.Session;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import sk.juvius.ulet.c;
import sk.juvius.ulet.projects.Project;
import sk.juvius.ulet.projects.User;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DialogController {

    private final ComboBox<Project> projects;
    private final ComboBox<User> users;
    private JDialog parent;
    private final Session session;
    private final Label curProjectLabel;
    private final Label userLabel;

    public DialogController(DialogView view, Session session) {
        projects = view.getprojectsComboBox();
        curProjectLabel = view.getCurProjectLabel();
        users = view.getUserComboBox();
        userLabel = view.getCurUserLabel();
        this.session = session;
    }

    public void loadConfig() {
        Platform.runLater(() -> {
            try {
                List<Project> projectList = c.projectManager.uploadConfig();
                projects.getItems().clear();
                projects.getItems().addAll(projectList);

                List<User> userList = c.projectManager.loadUsers();
                users.getItems().clear();
                users.getItems().addAll(userList);

                if(c.curProject != null) {
                    projects.setValue(c.curProject);
                } else {
                    projects.setValue(projectList.get(0));
                    setCurProjectLabelText("Not set");
                }

                if(c.curUser != null) {
                    users.setValue(c.curUser);
                } else {
                    users.setValue(userList.get(0));
                    setCurUserLabelText("Not set");
                }
            } catch (IOException e) {
            }
        });
    }

    public void setParent(JDialog parent) {
        this.parent = parent;
    }

    public void setCurProjectLabelText(String str) {
        curProjectLabel.setText("CURRENT PROJECT: " + str);
    }

    public void setCurUserLabelText(String str) {
        userLabel.setText("CURRENT USER: " + str);
    }

    public EventHandler connectionButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            Platform.runLater(() -> {
                Project selectedProject = projects.getValue();
                c.curProject = selectedProject;
                setCurProjectLabelText(selectedProject.getDisplayName());

                User selectedUser = users.getValue();
                c.curUser = selectedUser;
                setCurUserLabelText(selectedUser.getId() + " - " + selectedUser.getName());

                /*try {
                    session.SetConfigOption("search_path", "\""
                            + selectedProject.getModelPath().getAbsolutePath()
                            + "\"");
                    stringseq seq = stringseq.create();
                    seq.append(selectedProject.getDisplayName());
                    session.UIDisplayLocalizedMessage(
                            MinWi.MSG_FILE, "minwi_project_changed", seq);
                } catch (jxthrowable e) {
                }*/
                File serverLogFile = new File(
                        selectedProject.getMinwiProjectPath(), "minwi_log.csv");
                c.setServerLog(serverLogFile.getAbsolutePath());
                parent.setVisible(false);
            });
        }
    };

    public EventHandler cancelButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            Platform.runLater(() -> {
                parent.setVisible(false);
            });
        }
    };
}
