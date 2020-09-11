package sk.juvius.ulet.cmds.connect;

import com.ptc.pfc.pfcSession.Session;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;

public class Dialog {


    private final JDialog jDialog = new JDialog();
    private final JFXPanel jfx = new JFXPanel();
    private final Session session;
    private DialogController controller;

    public Dialog(Session session) {
        this.session = session;
        jDialog.setTitle("Select ShareMan Project");
        jDialog.setModal(true);
        jDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jDialog.setAlwaysOnTop(true);
        jDialog.setResizable(false);
        jDialog.add(jfx);
        jfx.setScene(initFX());
        jDialog.pack();
        makeCenter(jDialog);
    }

    public void show() {
        controller.loadConfig();
        jDialog.setVisible(true);
    }

    private void makeCenter(JDialog dialog) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - jDialog.getWidth()) / 2;
        int y = (screen.height - jDialog.getHeight()) / 2;
        dialog.setLocation(x, y);
    }

    private Scene initFX() {
        DialogView view = new DialogView(session);
        controller = view.getController();
        controller.setParent(jDialog);
        Scene scene = new Scene(view);
        return scene;
    }
}
