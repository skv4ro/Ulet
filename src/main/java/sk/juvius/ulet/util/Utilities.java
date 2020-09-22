package sk.juvius.ulet.util;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.pfc.pfcAssembly.Assembly;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcFeature.Feature;
import com.ptc.pfc.pfcFeature.FeatureStatus;
import com.ptc.pfc.pfcFeature.FeatureType;
import com.ptc.pfc.pfcFeature.Features;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcSession.FileListOpt;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcUI.MessageDialogOptions;
import com.ptc.pfc.pfcUI.MessageDialogType;
import com.ptc.pfc.pfcUI.pfcUI;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {
    private final Session session;
    private MessageDialogOptions errorMDO;

    public Utilities(Session session) throws jxthrowable {
        this.session = session;
        assignErrorMDO();
    }

    private void assignErrorMDO() throws jxthrowable {
        if(session == null) return;
        errorMDO = pfcUI.MessageDialogOptions_Create();
        errorMDO.SetDialogLabel("Error");
        errorMDO.SetMessageDialogType(MessageDialogType.MESSAGE_ERROR);
    }

    public void listActiveComponents(Assembly asm,
                                     List<ModelDescriptor> container)
            throws jxthrowable {
        Features feats = asm.ListFeaturesByType(
                true, FeatureType.FEATTYPE_COMPONENT);
        for(int i = 0; i < feats.getarraysize(); i++) {
            Feature feature = feats.get(i);
            ComponentFeat component = (ComponentFeat) feature;
            ModelDescriptor md = component.GetModelDescr();
            if(component.GetStatus() == FeatureStatus.FEAT_ACTIVE) {
                if(md.GetType() == ModelType.MDL_PART) {
                    container.add(md);
                } else {
                    container.add(md);
                    Assembly subAsm = (Assembly) session.GetModelFromDescr(md);
                    listActiveComponents(subAsm, container);
                }
            }
        }
    }

    public Map<String, String> listModelsToPathMap(String path, String filters)
            throws jxthrowable {
        Map<String, String> result = new HashMap<>();
        stringseq seq = session.ListFiles(
                filters, FileListOpt.FILE_LIST_LATEST, path);
        for(int i = 0; i < seq.getarraysize(); i++) {
            String absPath = seq.get(i);
            String modelFileName = getFileNameFromPath(absPath);
            result.put(modelFileName, absPath);
        }
        return result;
    }

    public int getFileVersionFromPath(String path) {
        int version = 0;
        File file = new File(path);
        String fileName = file.getName();
        try {
            version = Integer.parseInt(fileName.substring(
                    fileName.lastIndexOf(".") + 1, fileName.length()));
        } catch (NumberFormatException e) {
        }
        return version;
    }

    public String getFileNameFromPath(String absPath) {
        File file = new File(absPath);
        String name = file.getName();
        return removeVersionFromPath(name);
    }

    public String removeVersionFromPath(String path) {
        if(path.contains(".")) {
            int count = path.length() - path.replace(".", "").length();
            if(count != 1) {
                path = path.substring(0, path.lastIndexOf("."));
            }
        }
        return path.toLowerCase();
    }

    private List<String> listLastIterationFiles(String filter, File path) {
        List<String> result = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        Map<String, Integer> lastIterations = new HashMap<>();
        File[] files = path.listFiles();
        String[] filters = filter.replace(" ", "").split(",");

        for(File file : files) {
            if(!file.isDirectory()) fileNames.add(file.getAbsolutePath());
        }

        fileNames.forEach((name) -> {
            String onlyName = removeVersionFromPath(name);
            for(String filt : filters) {
                if(onlyName.endsWith(filt)) {
                    int version = getFileVersionFromPath(name);
                    if(!lastIterations.containsKey(onlyName)) {
                        lastIterations.put(onlyName, version);
                    } else {
                        int assigned = lastIterations.get(onlyName);
                        if(version > assigned) {
                            lastIterations.put(onlyName, version);
                        }
                    }
                }
            }
        });

        lastIterations.forEach((onlyName, version) -> {
            if(version >= 1) {
                result.add(onlyName + "." + version);
            } else {
                result.add(onlyName);
            }
        });

        return result;
    }

    public String getPath(ModelDescriptor md) throws jxthrowable {
        String device = md.GetDevice();
        String path = md.GetPath();
        if(device.isEmpty()) {
            return path;
        } else {
            return device + ":" + path;
        }
    }

    public static void infoMsg(Session session, String msg) throws jxthrowable {
        session.UIShowMessageDialog(msg, null);
    }

    public void infoMsg(String msg) throws jxthrowable {
        session.UIShowMessageDialog(msg, null);
    }

    public static void msgSwing(Object o) {
        JOptionPane.showMessageDialog(null, o);
    }

    public static String stackTraceToString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static void stackTraceToSwingMsg(Exception e) {
        Utilities.msgSwing(Utilities.stackTraceToString(e));
    }

    public void msgError(Object o) throws  jxthrowable {
        session.UIShowMessageDialog(o.toString(), getErrorMDO());
    }

    public MessageDialogOptions getErrorMDO() {
        return errorMDO;
    }
}
