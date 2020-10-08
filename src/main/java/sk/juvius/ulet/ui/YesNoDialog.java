package sk.juvius.ulet.ui;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcUI.*;

import java.awt.event.ActionListener;

public class YesNoDialog {

    private final MessageDialogOptions mdo;
    private final Session session;
    private String message;
    private MessageButton selectedButton;
    private ActionListener yesListener;
    private ActionListener noListener;

    public YesNoDialog(Session session, String title) throws jxthrowable {
        this(session, title, "default message");
    }

    public YesNoDialog(Session session, String title, String message) throws jxthrowable {
        this.session = session;
        this.message = message;
        mdo = pfcUI.MessageDialogOptions_Create();
        mdo.SetMessageDialogType(MessageDialogType.MESSAGE_QUESTION);
        mdo.SetDialogLabel(title);
        MessageButtons buttons = MessageButtons.create();
        buttons.append(MessageButton.MESSAGE_BUTTON_YES);
        buttons.append(MessageButton.MESSAGE_BUTTON_NO);
        mdo.SetButtons(buttons);
        mdo.SetDefaultButton(MessageButton.MESSAGE_BUTTON_NO);
    }

    public void show(String message) throws jxthrowable {
        selectedButton = session.UIShowMessageDialog(message, mdo);
        if(selectedButton.getValue() == MessageButton._MESSAGE_BUTTON_YES) {
            yesHandler();
        } else {
            noHandler();
        }
    }

    public void show() throws jxthrowable {
        show(message);
    }

    public void setVisible(boolean visible) throws jxthrowable {
        if(visible) show();
    }

    public MessageDialogOptions getMdo() {
        return mdo;
    }

    public MessageButton getSelectedButton() {
        return selectedButton;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActionListener getYesListener() {
        return yesListener;
    }

    public void setYesListener(ActionListener yesListener) {
        this.yesListener = yesListener;
    }

    public ActionListener getNoListener() {
        return noListener;
    }

    public void setNoListener(ActionListener noListener) {
        this.noListener = noListener;
    }

    private void yesHandler() {
        if(yesListener == null) return;
        yesListener.actionPerformed(null);
    }


    private void noHandler() {
        if(noListener == null) return;
        noListener.actionPerformed(null);
    }
}
