package sk.juvius.ulet.cmds.download;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcExceptions.XCancelProEAction;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcUI.*;
import org.apache.commons.io.FileUtils;
import sk.juvius.ulet.c;
import sk.juvius.ulet.util.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DownloadAll extends DefaultUICommandActionListener {
    private final Utilities utils;
    private final Session session;
    private final String filter = "*.prt,*.asm,*.drw";
    private final MessageDialogOptions askDialogOptions;
    private Map<String, String> serverModels;
    private Map<String, String> localModels;
    private int successful;
    private int failed;

    public DownloadAll(Session session) throws jxthrowable {
        this.session = session;
        this.utils = new Utilities(session);
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
    }

    @Override
    public void OnCommand() throws jxthrowable {
        if(c.curProject == null) {
            session.UIShowMessageDialog("There is no project selected!", null);
            return;
        }

        askUserToContinue();

        successful = 0;
        failed = 0;
        String workingDirectory = session.GetCurrentDirectory();
        File workDirFile = new File(workingDirectory);
        serverModels = utils.listModelsToPathMap(
                c.curProject.getModelPath().getAbsolutePath(),
                filter);
        localModels = utils.listModelsToPathMap(
                workingDirectory,
                filter);
        serverModels.forEach((modelFileName, serverFilePath) -> {
            String localFilePath = localModels.get(modelFileName);
            File serverFile = new File(serverFilePath);

            if(localFilePath == null) {
                try {
                    FileUtils.copyFileToDirectory(serverFile, workDirFile);
                    successful++;
                    c.localLog.info(c.USER_NAME + ";"
                            + serverFile.getAbsolutePath()
                            + ";has been successfully downloaded in;"
                            + workDirFile.getAbsolutePath()
                            + ";");
                } catch (IOException e) {
                    failed++;
                    c.localLog.error(c.USER_NAME + ";"
                            + serverFile.getAbsolutePath()
                            + ";has failed to download in;"
                            + workDirFile.getAbsolutePath() + ";"
                            + e.getMessage());
                }
            } else {
                int newLocalFileVersion =
                        utils.getFileVersionFromPath(localFilePath);
                File newLocalFile = new File(workDirFile,
                        modelFileName + "." + (newLocalFileVersion + 1));
                try {
                    FileUtils.copyFile(serverFile, newLocalFile);
                    successful++;
                    c.localLog.info(c.USER_NAME + ";"
                            + serverFile.getAbsolutePath()
                            + ";has been successfully downloaded as;"
                            + newLocalFile.getAbsolutePath() + ";"
                            + ";");
                } catch (IOException e) {
                    failed++;
                    c.localLog.error(c.USER_NAME + ";"
                            + serverFile.getAbsolutePath()
                            + ";has failed to download as;"
                            + newLocalFile.getAbsolutePath() + ";"
                            + e.getMessage());
                }
            }
        });
        session.EraseUndisplayedModels();
        session.UIShowMessageDialog(
                "New files have been downloaded into working directory."
                        + "\nSuccessfully downloaded " + successful + " files."
                        + "\nFailed to download " + failed + " files."
                        + "\nFor more details see local log file.",
                null);
    }

    private void askUserToContinue() throws jxthrowable {
        if(session.GetCurrentModel() != null) {
            session.UIShowMessageDialog(
                    "No model can be displayed to perform this action.",
                    null);
            XCancelProEAction.Throw();
        }
        MessageButton selected = session.UIShowMessageDialog(
                "This operation will erase all objects in session. "
                        + "Do you want to continue?", askDialogOptions);
        if(selected == MessageButton.MESSAGE_BUTTON_NO) {
            XCancelProEAction.Throw();
        }
    }
}
