
package sk.juvius.ulet;

import com.ptc.cipjava.jxthrowable;
import sk.juvius.ulet.commands.Command;
import sk.juvius.ulet.commands.login.LoginCmd;
import sk.juvius.ulet.commands.repository.RepositoryCmd;
import sk.juvius.ulet.commands.upload.UploadCmd;

import java.util.ArrayList;
import java.util.List;

public class AppBuilder {

    private final List<Command> commands = new ArrayList<>();

    public AppBuilder(AppContext context) throws jxthrowable {
        commands.add(new LoginCmd(context));
        commands.add(new RepositoryCmd(context));
        commands.add(new UploadCmd(context));
    }
    
    public void buildApp() {
        for(Command command : commands) {
            command.build(AppContext.getCommandsMsgFile());
        }
    }

    public Command getCommand(Class<?> clazz) {
        for(Command command : commands) {
            if(clazz.isInstance(command)) return command;
        }
        return null;
    }
}
