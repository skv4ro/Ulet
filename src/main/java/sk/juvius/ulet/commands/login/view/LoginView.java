package sk.juvius.ulet.commands.login.view;

import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.ui.Dialog;
import sk.juvius.ulet.ui.Panel;
import sk.juvius.ulet.util.R;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class LoginView extends Dialog {

    private final JTextField usernameTextField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JCheckBox rememberPasswordCheckBox = new JCheckBox("remember password");
    private final JCheckBox autoLoginCheckBox = new JCheckBox("auto login");
    private final JLabel messageLabel = new JLabel("invalid username or password");
    private final JButton loginButton = new JButton("login");
    private final Color verifiedColor =  new Color(0,0,0, 0);
    private final Color unverifiedColor = Color.red;

    public LoginView() {
        setTitle(AppContext.APP_NAME + " login");
        setIconImage(R.getImage("ulet16.png"));
        Panel content = new Panel();

        JLabel usernameLabel = new JLabel("username");
        JLabel passwordLabel = new JLabel("password");

        messageLabel.setForeground(verifiedColor);
        autoLoginCheckBox.setEnabled(false);
        autoLoginCheckBox.setToolTipText("Automatically log in on next startup.");

        GridBagConstraints gbc = new GridBagConstraints();
        int spacing = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(spacing,spacing,spacing,spacing);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        content.add(usernameLabel, gbc);
        gbc.gridy++;
        content.add(usernameTextField, gbc);
        gbc.gridy++;
        content.add(passwordLabel, gbc);
        gbc.gridy++;
        content.add(passwordField, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        content.add(rememberPasswordCheckBox, gbc);
        gbc.gridy++;
        content.add(autoLoginCheckBox, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(messageLabel, gbc);
        gbc.gridy++;
        content.add(loginButton, gbc);

        add(content);
        Dimension dim = content.getPreferredSize();
        dim.width = 256;
        content.setPreferredSize(dim);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        getRootPane().setDefaultButton(loginButton);
    }

    public void setUserNameText(String username) {
        usernameTextField.setText(username);
    }

    public void setPassword(char[] password) {
        passwordField.setText(new String(password));
    }

    public void setRememberLogin(boolean remember) {
        rememberPasswordCheckBox.setSelected(remember);
    }

    public void setAutoLogin(boolean autoLogin) {
        autoLoginCheckBox.setSelected(autoLogin);
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public void setLoginController(ActionListener controller) {
        loginButton.addActionListener(controller);
    }

    public String getUserName() {
        return usernameTextField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public boolean getRememberPassword() {
        return rememberPasswordCheckBox.isSelected();
    }

    public boolean getAutoLogin() {
        return autoLoginCheckBox.isSelected();
    }

    public void setRememberController(ActionListener controller) {
        rememberPasswordCheckBox.addActionListener(controller);
    }

    public void setUsernameTextFieldController(FocusListener controller) {
        usernameTextField.addFocusListener(controller);
    }

    public void setPasswordVerified(boolean verified) {
        if(verified) messageLabel.setForeground(verifiedColor);
        else messageLabel.setForeground(unverifiedColor);
    }

    public void setAutoLoginEnabled(boolean enabled) {
        autoLoginCheckBox.setEnabled(enabled);
    }

    public boolean getAutoLoginEnabled() {
        return autoLoginCheckBox.isEnabled();
    }

    public void reset() {
        usernameTextField.setText("");
        passwordField.setText("");
        messageLabel.setForeground(verifiedColor);
        rememberPasswordCheckBox.setSelected(false);
        autoLoginCheckBox.setSelected(false);
    }
}
