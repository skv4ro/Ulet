package sk.juvius.ulet.cmds.upload;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.FileListOpt;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.Solid;
import sk.juvius.ulet.util.Utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Uploader {

    private final Session session;
    private final Utilities utils;
    private final Checker checker = new Checker(this);
    private final FileUploader fileUploader;
    private final Map<String, ModelDescriptor> itemsToUpload = new HashMap<>();
    private final Map<String, Integer> workDirDrawingFiles = new HashMap<>();


    Uploader(Session session) throws jxthrowable {
        this.session = session;
        utils = new Utilities(session);
        fileUploader = new FileUploader(utils);
    }

    void upload(List<ModelDescriptor> toProcess) throws jxthrowable {
        try {
            getPreparedItems(toProcess);
            boolean result = checker.check(session, itemsToUpload);
            if (!result) return;
            checker.showDialog();
            checker.updateItems(itemsToUpload);

            if(!checker.shouldContinue()) return;

            fileUploader.processUpload(itemsToUpload);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            session.UIShowMessageDialog(errors.toString(), null);
        }
    }

    public void addSingleModel() throws jxthrowable {
        session.UIShowMessageDialog("Ahoj svet", null);
    }

    private void getPreparedItems(List<ModelDescriptor> toProcess) throws jxthrowable {
        itemsToUpload.clear();
        listDrawingsInWorkDir();
        for(ModelDescriptor md : toProcess) {
            String genericName = md.GetGenericName();
            String instanceName = md.GetInstanceName().toLowerCase();
            String fileName;

            if(genericName == null) {
                fileName = md.GetFileName().toLowerCase();
            } else {
                fileName = getGenericFileName(md);
                String genericDrawingInstanceName = fileName.substring(0, fileName.lastIndexOf("."));
                String genericDrawingFileName = genericDrawingInstanceName + ".drw";
                putDrawing(genericDrawingFileName);
            }

            String instanceDrawingFileName = instanceName + ".drw";
            putDrawing(instanceDrawingFileName);
            itemsToUpload.put(fileName, md);
        }
    }

    private void putDrawing(String fileName) throws jxthrowable {
        if(workDirDrawingFiles.containsKey(fileName)) {
            ModelDescriptor drawingModelDescriptor = createDrawingModelDescriptor(
                    fileName, workDirDrawingFiles.get(fileName));
            itemsToUpload.put(fileName, drawingModelDescriptor);
        }
    }

    private ModelDescriptor createDrawingModelDescriptor(String instanceName, Integer version) throws jxthrowable {
        if(instanceName.endsWith(".drw")) instanceName = instanceName.substring(0, instanceName.lastIndexOf("."));
        ModelDescriptor result = pfcModel.ModelDescriptor_Create(ModelType.MDL_DRAWING, instanceName, null);
        result.SetPath(session.GetCurrentDirectory());
        result.SetFileVersion(version);
        return result;
    }

    private void listDrawingsInWorkDir() throws jxthrowable {
        workDirDrawingFiles.clear();
        stringseq seq = session.ListFiles("*.drw", FileListOpt.FILE_LIST_LATEST, null);
        for(int i = 0; i < seq.getarraysize(); i++) {
            String item = seq.get(i);
            int version = utils.getFileVersionFromPath(item);
            workDirDrawingFiles.put(utils.getFileNameFromPath(item).toLowerCase(), version);
        }
    }

    private String getGenericFileName(ModelDescriptor md) throws jxthrowable {
        String genericName = md.GetGenericName();
        String fileName;
        if(genericName == null) {
            fileName = md.GetFileName().toLowerCase();
        } else {
            Solid solid = (Solid) session.GetModelFromDescr(md);
            fileName = solid.GetTopGenericInfo().GetFileName().toLowerCase();
        }
        return fileName;
    }
}
