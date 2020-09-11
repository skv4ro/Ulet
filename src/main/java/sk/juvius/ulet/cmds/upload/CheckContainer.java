package sk.juvius.ulet.cmds.upload;

import com.ptc.pfc.pfcModel.ModelDescriptor;
import sk.juvius.ulet.projects.Project;
import sk.juvius.ulet.projects.User;

import java.util.Map;

public class CheckContainer {
    private final Project project;
    private final User user;
    private final Map<String, ModelDescriptor> items;

    CheckContainer(Project project, User user, Map<String, ModelDescriptor> items) {
        this.project = project;
        this.user = user;
        this.items = items;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public Map<String, ModelDescriptor> getItems() {
        return items;
    }
}
