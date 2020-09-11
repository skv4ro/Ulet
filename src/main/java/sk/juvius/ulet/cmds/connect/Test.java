package sk.juvius.ulet.cmds.connect;

import javax.swing.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        /*ConnectDialog cd = new ConnectDialog();
        cd.setVisible(true);*/
        TestPlain tp = new TestPlain();
        tp.setVisible(true);
    }
}
