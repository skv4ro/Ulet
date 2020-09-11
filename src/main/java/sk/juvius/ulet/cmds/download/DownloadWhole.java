package sk.juvius.ulet.cmds.download;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcExceptions.XCancelProEAction;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcSelect.SelectionOptions;
import com.ptc.pfc.pfcSelect.Selections;
import com.ptc.pfc.pfcSelect.pfcSelect;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcUI.*;
import com.ptc.pfc.pfcWindow.Window;
import com.ptc.pfc.pfcWindow.Windows;
import sk.juvius.ulet.c;

import static sk.juvius.ulet.cmds.download.Downloader.TO_DOWNLOAD;
import static sk.juvius.ulet.cmds.download.Downloader.TO_RELOAD;

public class DownloadWhole extends DefaultUICommandActionListener {
    public static final String CMD_CODE = "download_whole";
    private final Downloader downloader;
    private final Session session;
    private final MessageDialogOptions askDialogOptions;
    private final SelectionOptions selOptions;

    public DownloadWhole(Session session) throws jxthrowable {
        this.session = session;
        this.downloader = new Downloader(session, "Download whole");
        MessageButton yesButton = MessageButton.MESSAGE_BUTTON_YES;
        MessageButton noButton = MessageButton.MESSAGE_BUTTON_NO;
        MessageButtons buttons = MessageButtons.create();
        buttons.append(yesButton);
        buttons.append(noButton);
        askDialogOptions = pfcUI.MessageDialogOptions_Create();
        askDialogOptions.SetMessageDialogType(
                MessageDialogType.MESSAGE_WARNING);
        askDialogOptions.SetDialogLabel("Warning");
        askDialogOptions.SetButtons(buttons);
        selOptions = pfcSelect.SelectionOptions_Create("prt_or_asm");
        selOptions.SetMaxNumSels(-1);
    }

    @Override
    public void OnCommand() throws jxthrowable {
        if(c.curProject == null) {
            session.UIShowMessageDialog("There is no project selected!", null);
            return;
        }

        askUserToContinue();

        Model curModel = session.GetCurrentModel();

        TO_DOWNLOAD.clear();
        TO_RELOAD.clear();

        if(curModel == null) {
            if(!downloader.downloadSingleCurModelNull()) return;
        } else {
            if(curModel.GetType() == ModelType.MDL_PART) {
                TO_DOWNLOAD.add(curModel.GetDescr());
            } else if(curModel.GetType() == ModelType.MDL_ASSEMBLY) {
                downloader.showSelectionMessage();
                Selections selections = session.Select(selOptions, null);
                for(int i = 0; i < selections.getarraysize(); i++) {
                    Model selectelModel = selections.get(i).GetSelModel();
                    if(selectelModel != null) TO_DOWNLOAD.add(selectelModel.GetDescr());
                }
            }

            Downloader.lastModelOpened = curModel.GetFileName().toLowerCase();

            Windows windows = session.ListWindows();
            for(int i = 0; i < windows.getarraysize(); i++) {
                Window window = windows.get(i);
                TO_RELOAD.add(window.GetModel().GetDescr());
                window.Close();
            }
        }

        session.RunMacro("~ Command `" + Downloader.CMD_NAME + "`;" + CMD_CODE + ";");
    }

    private void askUserToContinue() throws jxthrowable {
        MessageButton selected = session.UIShowMessageDialog(
                "This operation will erase all objects in session and reload displayed models. "
                        + "All unsaved work will be lost!s\n"
                        + "Do you want to continue?", askDialogOptions);
        if(selected == MessageButton.MESSAGE_BUTTON_NO) {
            XCancelProEAction.Throw();
        }
    }
}
