package sk.juvius.ulet.view;

import sk.juvius.ulet.AppContext;
import sk.juvius.ulet.R;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.ui.Dialog;
import sk.juvius.ulet.ui.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UDDecisionView extends Dialog {
    private final JLabel messageLabel = new JLabel();
    private final JButton singleButton = new JButton("single");
    private final JButton wholeButton = new JButton("whole");

    public UDDecisionView(boolean isUpload) {
        String decisionType = isUpload ? "Upload" : "Download";
        messageLabel.setText(createMsgText(decisionType.toLowerCase()));
        setTitle(decisionType + " decision");
        setIconImage(R.getImage("decision.png"));
        sk.juvius.ulet.ui.Panel content = new Panel();

        GridBagConstraints gbc = new GridBagConstraints();
        int spacing = 5;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(spacing,spacing,spacing,spacing);
        content.add(messageLabel, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        content.add(singleButton, gbc);
        gbc.gridx++;
        content.add(wholeButton, gbc);

        add(content);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private String createMsgText(String var) {
        return "<html>Do you want to " + var + "<br>single or whole model?</html>";
    }
}
