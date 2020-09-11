package sk.juvius.ulet.cmds.upload;

import java.io.File;

public class UploadItem {
    private final File oldFile;
    private final File newFile;

    public UploadItem(File oldFile, File newFile) {
        this.oldFile = oldFile;
        this.newFile = newFile;
    }

    public File getOldFile() {
        return oldFile;
    }

    public File getNewFile() {
        return newFile;
    }
}
