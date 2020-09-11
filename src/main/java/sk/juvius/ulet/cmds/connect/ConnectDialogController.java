package sk.juvius.ulet.cmds.connect;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import sk.juvius.ulet.projects.Project;
import sk.juvius.ulet.projects.User;

public class ConnectDialogController {

    @FXML
    Label curProjectLabel;
    @FXML
    Label curUserLabel;
    @FXML
    ComboBox<Project> projectComboBox;
    @FXML
    ComboBox<User> userComboBox;
}
