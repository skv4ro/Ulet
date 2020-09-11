package sk.juvius.ulet.cmds.connect;

import javax.swing.*;
import java.awt.*;

public class TestPlain extends JDialog {
    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    public TestPlain() {
        okButton.setPreferredSize(new Dimension(70, 25));
        cancelButton.setPreferredSize(new Dimension(70, 25));
        JPanel content = new JPanel(new FlowLayout());
        content.add(okButton);
        content.add(cancelButton);
        add(content);
        pack();
    }
}
