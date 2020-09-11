package sk.juvius.ulet.projects;

import org.apache.commons.io.FileUtils;
import sk.juvius.ulet.c;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    private final File minwiConfig;
    private final List<Project> projects = new ArrayList<>();

    private ProjectManager(File minwiConfig) throws IOException {
        this.minwiConfig = minwiConfig;
    }

    public static ProjectManager factoryProjectManager(File minwiConfig)
            throws IOException {
        ProjectManager pm = new ProjectManager(minwiConfig);
        pm.uploadConfig();
        return pm;
    }

    private void parseCsv(List<String> lines) {
        for(String line : lines) {
            if(line.trim().startsWith("!")) continue;
            String[] csvLine = line.split(";");
            if(csvLine.length == 3) {
                projects.add(new Project(csvLine[0],
                        new File(csvLine[1]),
                        new File(csvLine[2])));
            }
        }
    }

    public List<Project> uploadConfig() throws IOException {
        projects.clear();
        List<String> lines = FileUtils.readLines(
                minwiConfig, StandardCharsets.UTF_8);
        parseCsv(lines);
        return projects;
    }

    public List<User> loadUsers() throws IOException {
        List<String> lines = FileUtils.readLines(c.userFile, StandardCharsets.UTF_8);
        List<User> result = new ArrayList<>();
        for(String line : lines) {
            if(line.trim().startsWith("!")) continue;
            String id = line.substring(0, line.indexOf(":"));
            String name = line.substring(line.indexOf(":") + 1).trim();
            result.add(new User(id, name));
        };
        return result;
    }
}
