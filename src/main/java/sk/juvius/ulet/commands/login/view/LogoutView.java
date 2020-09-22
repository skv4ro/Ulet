package sk.juvius.ulet.commands.login.view;

import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.ui.Dialog;
import sk.juvius.ulet.ui.Panel;
import sk.juvius.ulet.util.R;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogoutView extends Dialog {

    private final JLabel usernameLabel = new JLabel();
    private final JButton logoutButton = new JButton("logout");

    public LogoutView() {
        setTitle(AppContext.APP_NAME + " logout");
        setIconImage(R.getImage("ulet16.png"));
        Panel content = new Panel();

        JLabel messageLabel = new JLabel("You are logged in as:");

        Font f = usernameLabel.getFont();
        usernameLabel.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        usernameLabel.setBorder(new EmptyBorder(10,10,10,10));

        GridBagConstraints gbc = new GridBagConstraints();
        int spacing = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(spacing,spacing,spacing,spacing);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        content.add(messageLabel, gbc);
        gbc.gridy++;
        content.add(usernameLabel, gbc);
        gbc.gridy++;
        content.add(logoutButton, gbc);

        add(content);
        Dimension dim = content.getPreferredSize();
        dim.width = 128;
        content.setPreferredSize(dim);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        getRootPane().setDefaultButton(logoutButton);
    }

    public void setUserLabelText(String userName) {
        usernameLabel.setText(userName);
    }

    public void setLogoutController(ActionListener controller) {
        logoutButton.addActionListener(controller);
    }
}
