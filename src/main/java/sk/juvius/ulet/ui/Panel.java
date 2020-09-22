package sk.juvius.ulet.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(10,10,10,10));
    }
}
