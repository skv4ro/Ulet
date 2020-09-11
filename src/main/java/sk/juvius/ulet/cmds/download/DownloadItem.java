package sk.juvius.ulet.cmds.download;

import java.io.File;

public class DownloadItem {
    private final File oldFile;
    private final File newFile;

    DownloadItem(File oldFile, File newFile) {
        this.oldFile = oldFile;
        this.newFile = newFile;
    }

    File getOldFile() {
        return oldFile;
    }

    File getNewFile() {
        return newFile;
    }
}
