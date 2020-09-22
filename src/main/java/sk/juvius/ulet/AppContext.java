package sk.juvius.ulet;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.pfc.pfcCommand.UICommand;
import com.ptc.pfc.pfcGlobal.pfcGlobal;
import com.ptc.pfc.pfcSession.Session;
import sk.juvius.ulet.commands.login.LoginCache;
import sk.juvius.ulet.commands.login.service.LoginService;
import sk.juvius.ulet.commands.login.service.UserService;
import sk.juvius.ulet.crypto.AES;
import sk.juvius.ulet.db.Crud;
import sk.juvius.ulet.db.DBManager;
import sk.juvius.ulet.db.impl.MySQLManager;
import sk.juvius.ulet.logging.Configuration;
import sk.juvius.ulet.logging.Logger;
import sk.juvius.ulet.util.Utilities;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class AppContext {

    public static final String APP_NAME = "Ulet";
    public static final String MSG_MAIN = "ulet_main.txt";
    public static final String MSG_COMMANDS = "ulet_cmd.txt";
    public static final String MSG_PATH = "ulet_path.txt";
    public static final String MSG_MSG = "ulet_msg.txt";
    private static AppContext instance;
    private static Logger logger;
    private static final String root;
    private String dbPath;

    public final MessageManager msgManager = new MessageManager();
    public final CreoCommands sysCommands = new CreoCommands();
    private final Session session;
    private final Utilities utils;
    private final File loginCacheFile = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Juvius\\Ulet", "login_data");
    private final LoginCache loginCache = new LoginCache(loginCacheFile);

    private File publicKeyFile = null;
    private File logConfigFile = new File("c:/applic/ulet.log.cfg");
    private File stdout = null;
    private LoginService loginService;

    static {
        URL rootUrl = AppContext.class.getClassLoader().getResource("");
        if(rootUrl == null) root = null;
        else root = rootUrl.getFile();
    }

    public class MessageManager {
        private void displayMsg(String msg) {
            try {
                session.UIDisplayLocalizedMessage(MSG_MSG, msg, null);
            } catch (jxthrowable ignored) {}
        }

        public void displaySuccessStartMsg() {
            displayMsg("start_success");
        }

        public void displayWrongModelTypeMsg() {
            displayMsg("wrong_model_type");
        }

        public void displaySelPartOrAsmPrompt() {
            displayMsg("sel_prt_or_asm_prompt");
        }

        public void displayNoSolidMsg() {
            displayMsg("no_solid_in_model");
        }

        public void displayModelTablePrompt() {displayMsg("model_table_prompt");}
    }

    public class CreoCommands {
        public static final String SKETCH = "ProCmdDatumSketCurve";
        public static final String HOLE = "ProCmdHole";

        private UICommand getSystemCmd(String strCmd) throws jxthrowable {
            return session.UIGetCommand(strCmd);
        }

        public UICommand getSketchCmd() throws jxthrowable {
            return getSystemCmd(SKETCH);
        }

        public UICommand getHoleCmd() throws jxthrowable {
            return getSystemCmd(HOLE);
        }
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Assign{
    }

    private AppContext() throws jxthrowable {
        AES.setKey("secret");
        loginCache.load();
        this.session = pfcGlobal.GetProESession();
        this.utils = new Utilities(session);
        assignAll();
        setStdout();
        logger = new Logger(new Configuration(logConfigFile));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            logger.debug(Utilities.stackTraceToString(e));
            logger.warn("Cannot set system laf.");
        }
    }

    public static AppContext getInstance() {
        if(instance == null) {
            try {
                instance = new AppContext();
            } catch (Exception e) {
                Utilities.stackTraceToSwingMsg(e);
            }
        }
        return instance;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void stupidDebug(File file) {
        try {
            System.setOut(
                    new PrintStream(file));
        } catch (FileNotFoundException e) {
            Utilities.stackTraceToSwingMsg(e);
        }
    }

    private void setStdout() {
        if(stdout != null) {
            stupidDebug(stdout);
        }
    }

    private void assignAll() {
        for(Method method : this.getClass().getDeclaredMethods()) {
            if(method.getAnnotation(Assign.class) != null) {
                try {
                    method.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getRoot() {
        if(root == null);// return new RootNotInCPException();
        return root;
    }

    public String getDbPath() {
        return dbPath;
    }

    private String loadFromMsgFile(String msgFile, String msg, stringseq seq) {
        try {
            return session.GetMessageContents(msgFile, msg, seq);
        } catch (jxthrowable e) {
            return null;
        }
    }

    private String loadFromPathMsgFile(String msg) {
        return loadFromMsgFile(MSG_PATH, msg, null);
    }

    private File fileFromOption(String msgPath) {
        String path = loadFromPathMsgFile(msgPath);
        if(path == null || path.toLowerCase().equals("null")) return null;
        return new File(path);
    }

    @Assign
    private void assignPublicKeyfile() {
        publicKeyFile = fileFromOption("ulet_public_key");
    }

    @Assign
    private void assignLoginService() {
        DBManager dbManager = new MySQLManager("10.2.0.26", 3306, "ulet", "ulet", "avsys1788");
        Crud crud = new Crud(dbManager);
        UserService userService = new UserService(crud);
        loginService = new LoginService(userService, getLoginCache());
    }

    public Session getSession() {
        return session;
    }

    public Utilities getUtils() {
        return utils;
    }

    public File getPublicKeyFile() {
        return publicKeyFile;
    }

    public LoginCache getLoginCache() {
        return loginCache;
    }

    public LoginService getLoginService() {
        return loginService;
    }
}
