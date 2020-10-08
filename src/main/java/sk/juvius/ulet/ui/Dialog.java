package sk.juvius.ulet.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Dialog extends JDialog {
    public Dialog() {
        setModal(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().registerKeyboardAction(e -> this.dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    //TODO dorobit add content metodu ktora bude packovat a nastavi relativnu poziciu na null
}
