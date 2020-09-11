package sk.juvius.ulet.cmds.upload.infodialog;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import sk.juvius.ulet.c;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AfterDialog {
    private final JDialog jDialog = new JDialog();
    private AfterDialogView infoListView;

    public AfterDialog() {
        jDialog.setTitle("Upload info");
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jDialog.setAlwaysOnTop(true);
        JFXPanel jfx = new JFXPanel();
        jDialog.add(jfx);
        jfx.setScene(initFX());
        jDialog.pack();
        jDialog.setMinimumSize(new Dimension(150, 300));
        jDialog.setSize(new Dimension(500,600));
        makeCenter(jDialog);
    }

    public void show() {
        jDialog.setVisible(true);
    }

    public void fill(java.util.List<String> successList,
                     List<String> failedList,
                     int successCount,
                     int failedCount,
                     int sucBackupCount,
                     int failBackupCount) {
        successList.sort(String::compareToIgnoreCase);
        failedList.sort(String::compareToIgnoreCase);
        ObservableList<String> sucObsList = infoListView.getSuccessListView().getItems();
        ObservableList<String> failObsList = infoListView.getFailedListView().getItems();
        sucObsList.clear();
        failObsList.clear();
        Platform.runLater(() -> {
            sucObsList.addAll(successList);
            failObsList.addAll(failedList);
            infoListView.getProjectLabel().setText("Project: " + c.curProject.getDisplayName());
            infoListView.getSuccessUploadLabel().setText("Successfully uploaded: " + successCount);
            infoListView.getFailedUploadLabel().setText("Failed to upload: " + failedCount);
            infoListView.getSuccessBackupLabel().setText("Successfully backuped: " + sucBackupCount);
            infoListView.getFailedBackupLabel().setText("Failed to backup: " + failBackupCount);
        });
    }

    private void makeCenter(JDialog dialog) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - jDialog.getWidth()) / 2;
        int y = (screen.height - jDialog.getHeight()) / 2;
        dialog.setLocation(x, y);
    }

    private Scene initFX() {
        infoListView = new AfterDialogView(jDialog);
        return new Scene(infoListView);
    }
}
