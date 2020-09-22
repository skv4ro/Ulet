package sk.juvius.ulet.util.tabledialog;

import com.ptc.pfc.pfcModel.ModelType;
import javafx.beans.property.*;

public class Record {

    private final boolean canChangeUpload;
    private SimpleStringProperty name;
    private SimpleStringProperty custom;
    private SimpleObjectProperty<ModelType> type;
    private SimpleIntegerProperty version;
    private SimpleStringProperty path;
    private SimpleBooleanProperty upload;
    private SimpleObjectProperty<StatusContainer> status;

    public Record(String name, String parameter, ModelType type, int version, String path, boolean upload, StatusContainer status, boolean canChangeUpload) {
        this.canChangeUpload = canChangeUpload;
        this.name = new SimpleStringProperty(name);
        this.custom = new SimpleStringProperty(parameter);
        this.type = new SimpleObjectProperty<>(type);
        this.version = new SimpleIntegerProperty(version);
        this.path = new SimpleStringProperty(path);
        this.upload = new SimpleBooleanProperty(upload);
        this.status = new SimpleObjectProperty<>(status);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCustom() {
        return custom.get();
    }

    public SimpleStringProperty customProperty() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom.set(custom);
    }

    public ObjectProperty<ModelType> typeProperty() {
        return type;
    }

    public ModelType getType() {
        return type.get();
    }

    public void setType(ModelType type) {
        this.type.set(type);
    }

    public IntegerProperty versionProperty() {
        return version;
    }

    public int getVersion() {
        return version.get();
    }

    public void setVersion(int version) {
        this.version.set(version);
    }

    public StringProperty pathProperty() {
        return path;
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public BooleanProperty uploadProperty() {
        return upload;
    }

    public boolean getUpload() {
        return upload.get();
    }

    public void setUpload(boolean upload) {
        if(canChangeUpload) this.upload.set(upload);
    }

    public ObjectProperty<StatusContainer> statusProperty() {
        return status;
    }

    public StatusContainer getStatus() {
        return status.get();
    }

    public void setStatus(StatusContainer status) {
        this.status.set(status);
    }
}
