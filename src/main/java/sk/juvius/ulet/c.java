package sk.juvius.ulet;

import com.ptc.pfc.pfcGlobal.pfcGlobal;
import com.ptc.pfc.pfcSession.Session;
import javafx.scene.image.Image;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sk.juvius.ulet.projects.Project;
import sk.juvius.ulet.projects.ProjectManager;
import sk.juvius.ulet.projects.User;
import sk.juvius.ulet.projects.WorkDirChangeHandler;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class c {

    public static final String MSG_PATH = "path.txt"; //Message file containing paths
    public static final String MSG_CMD = "cmd.txt"; //Message file containing commands labels and help texts
    public static final String MSG_MSG = "msg.txt"; //Message file containing messages displayed in message log
    public static String ROOT;

    public static String checkerPath;
    public static File userFile;
    public static Project curProject = null;
    public static User curUser = null;
    public static ProjectManager projectManager = null;
    public static final String USER_NAME = System.getProperty("user.name");
    public static Logger serverLog = Logger.getLogger("serverLogger");
    public static Logger localLog = Logger.getLogger("localLogger");
    private static Properties logConfig;

    public static void a() {

        JOptionPane.showMessageDialog(null, "");

        Session session; //Creo session object

        try {
            session = pfcGlobal.GetProESession();

            //GlobalContainer globalContainer = new GlobalContainer(session, c.class.getClassLoader());

            //Path to root directory of the app
            ROOT = session.GetLocalizedMessageContents(MSG_PATH, "root", null);

            checkerPath = session.GetLocalizedMessageContents(
                    MSG_PATH, "checker_path", null).trim();

            userFile = new File(session.GetLocalizedMessageContents(
                    MSG_PATH, "user_path", null));

            String sharemanConfigPath = session.GetLocalizedMessageContents(
                    MSG_PATH, "projects", null);
            projectManager = ProjectManager.factoryProjectManager(
                    new File(sharemanConfigPath));

            String localLogPath = session.GetLocalizedMessageContents(
                    MSG_PATH, "local_log_path", null
            );

            loadLogConfig(localLogPath);

            session.UIGetCommand("ProCmdSessionChangeDir").AddActionListener(
                    new WorkDirChangeHandler());

            //UIBuilder creates ui commands
            UIBuilder uiBuilder = new UIBuilder(session);
            uiBuilder.build();

            session.UIDisplayMessage(MSG_MSG, "started", null);
        } catch (Exception e) {
//            ErrorDialog.showErrorMsg(e);
        }
    }

    public static void b() {

    }

    public static File getResFile(String resource) {
        return new File(c.ROOT, "res/" + resource);
    }

    public static Image loadResImage(String name) {
        return new Image(getResFile(name).toURI().toString());
    }

    public static void setServerLog(String absPath) {
        if(logConfig != null) {
            logConfig.setProperty("log4j.appender.serverLogger.File", absPath);
            PropertyConfigurator.configure(logConfig);
        }
    }

    private static void loadLogConfig(String path) {
        logConfig = new Properties();
        File configFile = new File(ROOT, "log4j.properties");
        try {
            logConfig.load(new BufferedReader(new FileReader(configFile)));
            logConfig.setProperty(
                    "log4j.appender.localLogger.File", path);
            PropertyConfigurator.configure(logConfig);
        } catch (IOException e) {
        }
    }

    public static void debugMsg(Object msg) {
        System.out.println("Ulet" + ": " + msg);
    }

    public static void stackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        System.out.println("Ulet" + ": " + sw.toString());
    }
}
