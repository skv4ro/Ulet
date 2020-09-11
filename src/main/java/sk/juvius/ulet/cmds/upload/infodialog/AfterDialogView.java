package sk.juvius.ulet.cmds.upload.infodialog;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class AfterDialogView extends AnchorPane{
    private ListView<String> successListView;
    private ListView<String> failedListView;
    private Label successUploadLabel;
    private Label failedUploadLabel;
    private Label successBackupLabel;
    private Label failedBackupLabel;
    private Label projectLabel;

    public AfterDialogView(JDialog parent) {
        successListView = new ListView<>();
        failedListView = new ListView<>();
        successUploadLabel = new Label("Success");
        failedUploadLabel = new Label("Failed");
        successBackupLabel = new Label("Success");
        failedBackupLabel = new Label("Failed");
        projectLabel = new Label("Project");
        projectLabel.setStyle("-fx-font-weight: bold;");
        VBox successVbox = new VBox();
        VBox failedVbox = new VBox();
        VBox backupVbox = new VBox();
        successVbox.getChildren().addAll(successUploadLabel, successListView);
        failedVbox.getChildren().addAll(failedUploadLabel, failedListView);
        backupVbox.getChildren().addAll(successBackupLabel, failedBackupLabel);
        double offset = 5d;
        Button button = new Button("OK");
        button.setPrefWidth(55);
        button.setOnAction(event -> {
            Platform.runLater(() -> {
                parent.setVisible(false);
            });
        });
        VBox vBox = new VBox(projectLabel, successVbox, failedVbox, backupVbox, button);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setTopAnchor(vBox, offset);
        AnchorPane.setRightAnchor(vBox, offset);
        AnchorPane.setBottomAnchor(vBox, offset);
        AnchorPane.setLeftAnchor(vBox, offset);
        getChildren().addAll(vBox);
    }

    ListView<String> getSuccessListView() {
        return successListView;
    }

    ListView<String> getFailedListView() {
        return failedListView;
    }

    Label getSuccessUploadLabel() {
        return successUploadLabel;
    }

    Label getFailedUploadLabel() {
        return failedUploadLabel;
    }

    Label getFailedBackupLabel() {
        return failedBackupLabel;
    }

    Label getSuccessBackupLabel() {
        return successBackupLabel;
    }

    public Label getProjectLabel() {
        return projectLabel;
    }
}
