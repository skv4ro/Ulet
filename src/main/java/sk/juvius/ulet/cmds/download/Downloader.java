package sk.juvius.ulet.cmds.download;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcAssembly.Assembly;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcExceptions.XToolkitNotFound;
import com.ptc.pfc.pfcFamily.FamilyMember;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.RetrieveModelOptions;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcUI.FileOpenOptions;
import com.ptc.pfc.pfcUI.pfcUI;
import com.ptc.pfc.pfcWindow.Window;
import com.ptc.pfc.pfcWindow.Windows;
import org.apache.commons.io.FileUtils;
import sk.juvius.ulet.c;
import sk.juvius.ulet.util.Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Downloader extends DefaultUICommandActionListener {
    public static final String CMD_NAME = "shareman_downloader.cmd";
    static final List<ModelDescriptor> TO_DOWNLOAD = new ArrayList<>();
    static final List<ModelDescriptor> TO_RELOAD = new ArrayList<>();
    static String lastModelOpened;
    private final List<String> itemsToProcess = new ArrayList<>();
    private final List<DownloadItem> filesToCopy = new ArrayList<>();
    private final Utilities utils;
    private final Session session;
    private final String filter = "*.prt,*.asm,*.drw";
    private final FileOpenOptions openOptions;
    private final List<DownloadItem> failedToDownload = new ArrayList<>();
    private Map<String, String> serverModels;
    private Map<String, String> localModels;
    private int successful;
    private int failed;
    private String workDir;

    public Downloader(Session session, String openDialogTitle) throws jxthrowable {
        this.session = session;
        this.utils = new Utilities(session);
        openOptions = pfcUI.FileOpenOptions_Create("*.prt,*.asm");
        openOptions.SetDialogLabel(openDialogTitle);
    }

    @Override
    public void OnCommand() throws jxthrowable {
        String code = session.UIReadStringMessage(true);
        boolean switcher = false;
        filesToCopy.clear();
        itemsToProcess.clear();
        successful = 0;
        failed = 0;
        workDir = session.GetCurrentDirectory();
        serverModels = utils.listModelsToPathMap(
                c.curProject.getModelPath().getAbsolutePath(),
                filter);
        localModels = utils.listModelsToPathMap(
                workDir,
                filter);
        if (code.equals(DownloadSingle.CMD_CODE)) {
            switcher = true;
            for (ModelDescriptor md : TO_DOWNLOAD) {
                processPrepare(md);
            }
            session.EraseUndisplayedModels();
        } else if (code.equals(DownloadWhole.CMD_CODE)) {
            switcher = true;
            List<ModelDescriptor> newToDownload = new ArrayList<>();
            session.EraseUndisplayedModels();
            for (ModelDescriptor md : TO_DOWNLOAD) {
                ModelType type = md.GetType();
                if (type == ModelType.MDL_PART) {
                    newToDownload.add(md);
                } else if (type == ModelType.MDL_ASSEMBLY) {
                    newToDownload.add(md);
                    RetrieveModelOptions rmo = pfcSession.RetrieveModelOptions_Create();
                    ModelDescriptor desc = pfcModel.ModelDescriptor_Create(
                            ModelType.MDL_ASSEMBLY, md.GetInstanceName(), md.GetGenericName());
                    desc.SetPath(c.curProject.getModelPath().getAbsolutePath());
                    try {
                        Assembly asm = (Assembly) session.RetrieveModelWithOpts(desc, rmo);
                        utils.listActiveComponents(asm, newToDownload);
                    } catch (XToolkitNotFound e) {
                        System.out.println(e + " " + md.GetFileName());
                    }
                }
            }
            for (ModelDescriptor md : newToDownload) {
                processPrepare(md);
            }
            session.EraseUndisplayedModels();
        }

        if (switcher) {
            filesToCopy.forEach((downloadItem) -> {
                try {
                    FileUtils.copyFile(downloadItem.getOldFile(),
                            downloadItem.getNewFile());
                    c.localLog.info(c.USER_NAME + ";"
                            + downloadItem.getOldFile().getAbsolutePath()
                            + ";has been successfully downloaded as;"
                            + downloadItem.getNewFile().getAbsolutePath()
                            + ";");
                    successful++;
                } catch (IOException e) {
                    failed++;
                    c.localLog.error(c.USER_NAME + ";"
                            + downloadItem.getOldFile().getAbsolutePath()
                            + ";has failed to download in;"
                            + downloadItem.getNewFile().getAbsolutePath() + ";"
                            + e.getMessage());
                }
            });

            File workDirFile = new File(workDir);
            File[] files = workDirFile.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().endsWith(".xpr") || f.getName().endsWith(".xps")) {
                        f.delete();
                    }
                }
            }

            for (ModelDescriptor md : TO_RELOAD) {
                ModelType type = md.GetType();
                String instName = md.GetInstanceName();
                String genName = md.GetGenericName();
                ModelDescriptor desc = pfcModel.ModelDescriptor_Create(type, instName, genName);
                try {
                    session.OpenFile(desc);
                } catch (jxthrowable e) {
                    e.printStackTrace(System.out);
                }
            }

            Windows windows = session.ListWindows();
            for (int i = 0; i < windows.getarraysize(); i++) {
                Window window = windows.get(i);
                Model model = window.GetModel();
                if (model != null) {
                    if (model.GetFileName().toLowerCase().equals(lastModelOpened)) window.Activate();
                }
            }

            session.UIShowMessageDialog(
                    "New files have been downloaded into working directory."
                            + "\nSuccessfully downloaded " + successful + " files."
                            + "\nFailed to download " + failed + " files."
                            + "\nFor more details see local log file.",
                    null);
        }
    }

    void showSelectionMessage() {
        try {
            session.UIDisplayMessage(c.MSG_MSG, "select_prompt", null);
        } catch(jxthrowable ignored) {
        }
    }

    private File createNewerFileFromAbsPath(String absPath) {
        int version = utils.getFileVersionFromPath(absPath);
        String fileName = utils.getFileNameFromPath(absPath);
        File file = new File(absPath);
        String corectAbsPath = file.getAbsolutePath();
        return new File(corectAbsPath
                .substring(0, corectAbsPath.lastIndexOf(File.separator)),
                fileName + "." + (version + 1));
    }

    private void prepareFile(String name) {
        if(itemsToProcess.contains(name)) {
            return;
        } else {
            itemsToProcess.add(name);
        }
        String serverModelAbsPath = serverModels.get(name);
        String localModelAbsPath = localModels.get(name);
        if(serverModelAbsPath == null) return;
        if(localModelAbsPath != null) {
            filesToCopy.add(new DownloadItem(
                    new File(serverModels.get(name)),
                    createNewerFileFromAbsPath(localModelAbsPath)));
        } else {
            filesToCopy.add(new DownloadItem(
                    new File(serverModels.get(name)),
                    new File(workDir, name + "." + utils.getFileVersionFromPath(serverModelAbsPath))));
        }
    }

    private void processPrepare(ModelDescriptor md) throws jxthrowable {
        String genericName = md.GetGenericName();
        if(genericName == null) {
            String fileName = md.GetFileName().toLowerCase();
            String drwName = md.GetInstanceName().toLowerCase() + ".drw";
            prepareFile(fileName);
            prepareFile(drwName);
        } else {
            Solid solid = (Solid) session.GetModelFromDescr(md);
            String topGenericInstName = ((FamilyMember) solid).GetTopGenericInfo().GetInstanceName().toLowerCase();
            String topGenericFileName = topGenericInstName + "." + md.GetExtension();
            String topGenericDrwFileName = topGenericInstName + ".drw";
            String instanceDrwFileName = md.GetInstanceName().toLowerCase() + ".drw";
            prepareFile(topGenericFileName);
            prepareFile(topGenericDrwFileName);
            prepareFile(instanceDrwFileName);
        }
    }

    boolean downloadSingleCurModelNull() throws jxthrowable {
        String minwiProjectModelPath = c.curProject.getModelPath().getAbsolutePath();
        openOptions.SetDefaultPath(minwiProjectModelPath);
        String selected = session.UIOpenFile(openOptions);
        File selFile = new File(selected);
        File selFileParent = selFile.getParentFile();
        try {
            if(Files.isSameFile(selFileParent.toPath(), c.curProject.getModelPath().toPath())) {
                ModelDescriptor md = pfcModel.ModelDescriptor_CreateFromFileName(utils.getFileNameFromPath(selected));
                md.SetPath(c.curProject.getModelPath().getAbsolutePath());
                md.SetFileVersion(utils.getFileVersionFromPath(selected));
                TO_DOWNLOAD.add(md);
            } else {
                session.UIShowMessageDialog("Selected file is not from project folder!", null);
                return false;
            }
        } catch (IOException e) {
            System.out.println("Download single: " + e);
        }
        return true;
    }
}
