package sk.juvius.ulet.ui;

import javax.swing.*;

public class Dialog extends JDialog {
    public Dialog() {
        setModal(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
