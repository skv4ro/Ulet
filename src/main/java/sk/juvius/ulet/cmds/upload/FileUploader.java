package sk.juvius.ulet.cmds.upload;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import org.apache.commons.io.FileUtils;
import sk.juvius.ulet.c;
import sk.juvius.ulet.cmds.upload.infodialog.AfterDialog;
import sk.juvius.ulet.util.Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class FileUploader {

    private final Utilities utils;
    private final List<UploadItem> filesToCopy = new ArrayList<>();
    private final List<UploadItem> filesToMove = new ArrayList<>();
    private final List<String> successUploaded = new ArrayList<>();
    private final List<String> failedToUpload = new ArrayList<>();
    private final AfterDialog dialog = new AfterDialog();

    FileUploader(Utilities utils) {
        this.utils = utils;
    }

    void processUpload(Map<String, ModelDescriptor> itemsToUpload) throws jxthrowable {
        filesToCopy.clear();
        filesToMove.clear();
        successUploaded.clear();
        failedToUpload.clear();

        int uploadSuccessful = 0,
                uploadFailed = 0,
                moveSuccessful = 0,
                moveFailed = 0;

        Map<String, String> serverFiles = utils.listModelsToPathMap(
                c.curProject.getModelPath().getAbsolutePath(), "*.prt,*.asm,*.drw");


        for(String fileName : itemsToUpload.keySet()) {
            ModelDescriptor modelDescriptor = itemsToUpload.get(fileName);

            File path = new File(utils.getPath(modelDescriptor));
            int version = modelDescriptor.GetFileVersion();

            File localFile = new File(path, fileName + "." + version);

            if(serverFiles.containsKey(fileName)) {
                String serverFileAbsPath = serverFiles.get(fileName);
                File serverOldFile = new File(serverFileAbsPath);
                File serverNewFile = createNewerServerFile(fileName, serverFileAbsPath);
                File backupDir = new File(c.curProject.getMinwiProjectPath(), "backup");
                File serverBackupFile = createBackupServerFile(serverFileAbsPath, backupDir);

                filesToCopy.add(new UploadItem(localFile, serverNewFile));
                filesToMove.add(new UploadItem(serverOldFile, serverBackupFile));
            } else {
                File serverFile = new File(c.curProject.getModelPath(), localFile.getName());

                filesToCopy.add(new UploadItem(localFile, serverFile));
            }
        }

        for(UploadItem uploadItem : filesToCopy) {
            try {
                FileUtils.copyFile(
                        uploadItem.getOldFile(), uploadItem.getNewFile());
                uploadSuccessful++;
                successUploaded.add(uploadItem.getOldFile().getName());
                c.serverLog.info(c.USER_NAME + ";"
                        + uploadItem.getOldFile().getAbsolutePath()
                        + ";has been successfully uploaded as;"
                        + uploadItem.getNewFile().getAbsolutePath()
                        + ";");
            } catch (IOException e) {
                uploadFailed++;
                failedToUpload.add(uploadItem.getOldFile().getName());
                c.serverLog.error(c.USER_NAME + ";"
                        + uploadItem.getOldFile().getAbsolutePath()
                        + ";has failed to upload as;"
                        + uploadItem.getNewFile().getAbsolutePath() + ";"
                        + e.getMessage());
            }
        }

        for(UploadItem uploadItem : filesToMove) {
            try {
                FileUtils.moveFile(
                        uploadItem.getOldFile(), uploadItem.getNewFile());
                moveSuccessful++;
                c.serverLog.info(c.USER_NAME + ";"
                        + uploadItem.getOldFile().getAbsolutePath()
                        + ";has been successfully backuped as;"
                        + uploadItem.getNewFile().getAbsolutePath()
                        + ";");
            } catch (IOException e) {
                moveFailed++;
                c.serverLog.warn(c.USER_NAME + ";"
                        + uploadItem.getOldFile().getAbsolutePath()
                        + ";has failed to backup as;"
                        + uploadItem.getNewFile().getAbsolutePath() + ";"
                        + e.getMessage());
            }
        }

        dialog.fill(successUploaded, failedToUpload, uploadSuccessful, uploadFailed, moveSuccessful, moveFailed);
        dialog.show();
    }

    private File createNewerServerFile(String fileName, String absPath) {
        int version = utils.getFileVersionFromPath(absPath);
        File file = new File(absPath);
        String correctAbsPath = file.getAbsolutePath();
        return new File(correctAbsPath.substring(0, correctAbsPath.lastIndexOf(File.separator)),
                fileName + "." + (version + 1));
    }

    private File createBackupServerFile(String absPath, File backupDir) {
        File file = new File(absPath);
        String fileName = file.getName();
        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeString = dateTime.toString().replace(":", ".");
        String user = System.getProperty("user.name");
        String newFileName = dateTimeString + "_" + user + "_" + fileName;
        return new File(backupDir, newFileName);
    }
}
