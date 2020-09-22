package sk.juvius.ulet.util.tabledialog;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import sk.juvius.ulet.c;
import sk.juvius.ulet.cmds.upload.Uploader;
import sk.juvius.ulet.projects.Project;
import sk.juvius.ulet.projects.User;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class TableDialog extends JDialog {

    private TableDialogController controller;
    private boolean allowed;

    public TableDialog(Uploader uploader) {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(true);
        setAlwaysOnTop(true);
        JFXPanel jfxPanel = new JFXPanel();
        setupCustomTooltipBehavior(0, Integer.MAX_VALUE, 0);
        add(jfxPanel);
        jfxPanel.setScene(getScene());
        controller.setParent(this);
        controller.setUploader(uploader);
        pack();
        makeCenter();
    }

    public void setItems(ObservableList<Record> items) {
        controller.getTable().setItems(items);
    }

    public ObservableList<Record> getItems() {
        return controller.getTable().getItems();
    }

    private Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setClassLoader(c.class.getClassLoader());
            loader.setLocation(c.class.getClassLoader().getResource("/../res/View.fxml"));
            Scene scene = new Scene(loader.load());
            controller = loader.getController();
            return scene;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    private void makeCenter() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - this.getWidth()) / 2;
        int y = (screen.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    private void setupCustomTooltipBehavior(int openDelayInMillis, int visibleDurationInMillis, int closeDelayInMillis) {
        try {

            Class TTBehaviourClass = null;
            Class<?>[] declaredClasses = Tooltip.class.getDeclaredClasses();
            for (Class c:declaredClasses) {
                if (c.getCanonicalName().equals("javafx.scene.control.Tooltip.TooltipBehavior")) {
                    TTBehaviourClass = c;
                    break;
                }
            }
            if (TTBehaviourClass == null) {
                // abort
                return;
            }
            Constructor constructor = TTBehaviourClass.getDeclaredConstructor(
                    Duration.class, Duration.class, Duration.class, boolean.class);
            if (constructor == null) {
                // abort
                return;
            }
            constructor.setAccessible(true);
            Object newTTBehaviour = constructor.newInstance(
                    new Duration(openDelayInMillis), new Duration(visibleDurationInMillis),
                    new Duration(closeDelayInMillis), false);
            if (newTTBehaviour == null) {
                // abort
                return;
            }
            Field ttbehaviourField = Tooltip.class.getDeclaredField("BEHAVIOR");
            if (ttbehaviourField == null) {
                // abort
                return;
            }
            ttbehaviourField.setAccessible(true);

            // Cache the default behavior if needed.
            Object defaultTTBehavior = ttbehaviourField.get(Tooltip.class);
            ttbehaviourField.set(Tooltip.class, newTTBehaviour);

        } catch (Exception e) {
            System.out.println("Aborted setup due to error:" + e.getMessage());
        }
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public void setProjectAndUserLabel(Project project, User user) {
        Platform.runLater(() -> {
            controller.getProjectLabel().setText(project.getDisplayName());
            controller.getUserLabel().setText(user.getId() + " - " + user.getName());
        });
    }
}
