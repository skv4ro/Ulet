package sk.juvius.ulet.context;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcUI.MessageDialogOptions;
import com.ptc.pfc.pfcUI.MessageDialogType;
import com.ptc.pfc.pfcUI.pfcUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.AppContext;

public class MessageManager {

    private final static Logger log = LoggerFactory.getLogger(MessageManager.class);

    private final Session session;
    private final String msgFile;
    private final MessageDialogOptions warnMDO;

    public MessageManager(Session session, String msgFile) throws jxthrowable {
        this.session = session;
        this.msgFile = msgFile;
        this.warnMDO = createWarnMDO();
    }

    public void displaySuccessStartMsg() {
        displayMsg("start_success");
    }

    public void displayWrongModelTypeMsg() {
        displayMsg("wrong_model_type");
    }

    public void displaySelPartOrAsmPrompt() {
        displayMsg("sel_prt_or_asm_prompt");
    }

    public void displayNoSolidMsg() {
        displayMsg("no_solid_in_model");
    }

    public void displayModelTablePrompt() {displayMsg("model_table_prompt");}

    public void displayLoginMsg(String username) {
        displayMsg("logged_in", username);
    }

    public void displayRepoSelectedMsg(String repoName) {
        displayMsg("repo_selected", repoName);
    }

    public void showWarningDialog(String msg) throws jxthrowable {
        session.UIShowMessageDialog(msg, warnMDO);
    }

    private MessageDialogOptions createWarnMDO() throws jxthrowable {
        MessageDialogOptions mdo = pfcUI.MessageDialogOptions_Create();
        mdo.SetDialogLabel(AppContext.APP_NAME + " warning");
        mdo.SetMessageDialogType(MessageDialogType.MESSAGE_WARNING);
        return mdo;
    }

    private void displayMsg(String msg, String... vars) {
        try {
            stringseq seq = null;
            if(vars != null) {
                seq = stringseq.create();
                for(String var : vars) {
                    seq.append(var);
                }
            }
            session.UIDisplayLocalizedMessage(msgFile, msg, seq);
        } catch (jxthrowable e) {
            log.debug("Cannot display message: " + e.getMessage());
        }
    }
}
