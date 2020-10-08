package sk.juvius.ulet.model;

public class Repository {

    private final int id;
    private final String path;
    private final String backupPath;
    private final String displayName;

    public Repository(int id, String displayName, String path) {
        this(id, displayName, path, null);
    }

    public Repository(int id, String displayName, String path, String backupPath) {
        this.id = id;
        this.path = path;
        this.backupPath = backupPath;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
