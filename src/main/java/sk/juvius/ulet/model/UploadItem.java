package sk.juvius.ulet.model;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UploadItem {
    private final ModelType type;
    private final String instanceName;
    private final String genericName;
    private final int fileVersion;
    private final List<Object> customList = new ArrayList<>();
    private final String path;

    public UploadItem(ModelType type, String instanceName, String genericName, int fileVersion, String path) {
        this.type = type;
        this.instanceName = StringUtils.lowerCase(instanceName);
        this.genericName = StringUtils.lowerCase(genericName);
        this.fileVersion = fileVersion;
        this.path = path;
    }

    public UploadItem(ModelDescriptor md) throws jxthrowable {
        this.type = md.GetType();
        this.instanceName = StringUtils.lowerCase(md.GetInstanceName());
        this.genericName = StringUtils.lowerCase(md.GetGenericName());
        this.fileVersion = md.GetFileVersion();
        this.path = createPath(md);
    }

    public String getInstanceName() {
        return instanceName;
    }

    public String getGenericName() {
        return genericName;
    }
    public int getFileVersion() {
        return fileVersion;
    }

    public List<Object> getCustomList() {
        return customList;
    }

    public ModelType getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    private String createPath(ModelDescriptor md) throws jxthrowable {
        String device = md.GetDevice();
        String path = md.GetPath();
        if(device != null && !device.isEmpty()) return device + ":" + path;
        else return path;
    }
}
