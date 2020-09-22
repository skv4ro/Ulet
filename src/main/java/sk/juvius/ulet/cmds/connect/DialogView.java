package sk.juvius.ulet.cmds.connect;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import sk.juvius.ulet.projects.Project;
import sk.juvius.ulet.projects.User;

public class DialogView extends AnchorPane {

    private final VBox vBox;
    private final VBox vBox0;
    private final ComboBox<Project> projectsComboBox;
    private final HBox hBox;
    private final Button connectButton;
    private final Button cancelButton;
    private final DialogController controller;
    private final Label curProjectLabel;
    private final Label curUserLabel;
    private final ComboBox<User> userComboBox;

    public DialogView() {
        vBox = new VBox();
        vBox0 = new VBox();
        projectsComboBox = new ComboBox();
        userComboBox = new ComboBox<>();
        hBox = new HBox();
        connectButton = new Button();
        cancelButton = new Button();
        curProjectLabel = new Label("CURRENT PROJECT: Not set");
        curUserLabel = new Label("CURRENT USER: Not set");

        setId("AnchorPane");
        setPrefWidth(500.0);

        AnchorPane.setBottomAnchor(vBox, 10.0);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        AnchorPane.setRightAnchor(vBox, 10.0);
        AnchorPane.setTopAnchor(vBox, 10.0);
        vBox.setLayoutX(115.0);
        vBox.setLayoutY(51.0);

        vBox0.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox0.setSpacing(10.0);

        projectsComboBox.setPrefWidth(500.0);
        userComboBox.setPrefWidth(250);

        hBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        hBox.setSpacing(10.0);

        connectButton.setMnemonicParsing(false);
        connectButton.setPrefWidth(70.0);
        connectButton.setText("OK");
        cancelButton.setMnemonicParsing(true);
        cancelButton.setPrefWidth(70.0);
        cancelButton.setText("Cancel");
        curProjectLabel.setStyle("-fx-font-weight: bold;");
        VBox.setMargin(hBox, new Insets(10.0, 0.0, 0.0, 0.0));

        vBox0.getChildren().add(curProjectLabel);
        vBox0.getChildren().add(curUserLabel);
        vBox0.getChildren().add(projectsComboBox);
        vBox0.getChildren().add(userComboBox);
        vBox.getChildren().add(vBox0);
        hBox.getChildren().add(connectButton);
        hBox.getChildren().add(cancelButton);
        vBox.getChildren().add(hBox);
        getChildren().add(vBox);

        projectsComboBox.setConverter(new StringConverter<Project>() {
            @Override
            public String toString(Project object) {
                return object.getDisplayName();
            }

            @Override
            public Project fromString(String string) {
                return null;
            }
        });

        userComboBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User object) {
                return object.getId() + " - " + object.getName();
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        });

        this.controller = new DialogController(this);
        connectButton.setOnAction(controller.connectionButtonHandler);
        cancelButton.setOnAction(controller.cancelButtonHandler);
    }

    public ComboBox<Project> getprojectsComboBox() {
        return projectsComboBox;
    }

    public ComboBox<User> getUserComboBox() {
        return userComboBox;
    }

    public DialogController getController() {
        return controller;
    }

    public Label getCurProjectLabel() {
        return curProjectLabel;
    }

    public Label getCurUserLabel() {
        return curUserLabel;
    }
}
