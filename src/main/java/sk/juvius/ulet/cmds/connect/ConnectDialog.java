package sk.juvius.ulet.cmds.connect;

import javax.swing.*;

public class ConnectDialog extends JDialog {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel jTestPanel;

    public ConnectDialog() {
        setContentPane(jTestPanel);
        pack();
    }
}
