package sk.juvius.ulet.projects;

import java.io.File;

public class Project {

    private final String displayName;
    private final File modelPath;
    private final File minwiProjectPath;

    public Project(String displayName, File modelDir, File minwiProjectPath) {
        this.displayName = displayName;
        this.modelPath = modelDir;
        this.minwiProjectPath = minwiProjectPath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public File getModelPath() {
        return modelPath;
    }

    public File getMinwiProjectPath() {
        return minwiProjectPath;
    }

    @Override
    public String toString() {
        return displayName + "\n"
                + modelPath.getAbsolutePath() + "\n"
                + minwiProjectPath.getAbsolutePath();
    }
}
