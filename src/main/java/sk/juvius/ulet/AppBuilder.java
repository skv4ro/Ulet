
package sk.juvius.ulet;

import com.ptc.cipjava.jxthrowable;
import sk.juvius.ulet.commands.Command;
import sk.juvius.ulet.commands.login.Login;

import java.util.ArrayList;
import java.util.List;

public class AppBuilder {

    private final List<Command> commands = new ArrayList<>();

    public AppBuilder(AppContext context) throws jxthrowable {
        commands.add(new Login(context));
    }
    
    public void buildApp() {
        for(Command command : commands) {
            command.build(AppContext.MSG_COMMANDS);
        }
    }
}
