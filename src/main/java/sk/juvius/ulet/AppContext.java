package sk.juvius.ulet;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.pfc.pfcGlobal.pfcGlobal;
import com.ptc.pfc.pfcSession.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.commands.login.LoginCmd;
import sk.juvius.ulet.context.CreoCommands;
import sk.juvius.ulet.context.MessageManager;
import sk.juvius.ulet.context.ServicesContext;
import sk.juvius.ulet.db.CRUD;
import sk.juvius.ulet.crypto.AES;
import sk.juvius.ulet.db.DBManager;
import sk.juvius.ulet.db.impl.MySQLManager;
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
import java.nio.ByteBuffer;

public class AppContext {

    public static final String APP_NAME = "Ulet";

    private static final String mainMsgFile = "ulet_main.txt";
    private static final String commandsMsgFile = "ulet_cmd.txt";
    private static final String pathMsgFile = "ulet_path.txt";
    private static final String msgMsgFile = "ulet_msg.txt";
    private static final Logger log = LoggerFactory.getLogger(AppContext.class);
    private static AppContext instance;
    private static final String root;
    private final DBManager dbManager;
    private final CRUD crud;
    public final ServicesContext services;
    public final MessageManager messages;
    public final CreoCommands creoCommands;
    private final Session session;
    private final Utilities utils;
    private final File appDataUletFile = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Juvius\\Ulet");
    private File publicKeyFile = null;
    private File logConfigFile = new File("c:/applic/ulet.log.cfg");
    private File stdout = null;
    private final AppBuilder appBuilder;

    static {
        URL rootUrl = AppContext.class.getClassLoader().getResource("");
        if(rootUrl == null) root = null;
        else root = rootUrl.getFile();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Assign{
    }

    private AppContext() throws jxthrowable {
        setAesKey();
        this.session = pfcGlobal.GetProESession();
        this.dbManager = new MySQLManager("10.2.0.26", 3306, "ulet", "ulet", "avsys1788");
        this.crud = new CRUD(dbManager);
        this.messages = new MessageManager(session, msgMsgFile);
        this.services = new ServicesContext(crud, appDataUletFile, messages);
        this.utils = new Utilities(session);
        this.creoCommands = new CreoCommands(session);
        this.appBuilder = new AppBuilder(this);
        assignAll();
        setStdout();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            log.info("System laf successfully set");
        } catch (Exception e) {
            log.debug(Utilities.stackTraceToString(e));
            log.warn("Cannot set system laf.");
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

    public static void stupidDebug(File file) {
        try {
            System.setOut(
                    new PrintStream(file));
        } catch (FileNotFoundException e) {
            Utilities.stackTraceToSwingMsg(e);
        }
    }

    private void setAesKey() {
        String pcName = System.getenv("COMPUTERNAME");
        String username = System.getProperty("user.name");
        byte[] random = new byte[]{-12, 13, 95, 121, 18, -62, 35, 0};
        byte[] pcNameBytes = pcName.getBytes();
        byte[] usernameBytes = username.getBytes();
        byte[] key = ByteBuffer.allocate(pcNameBytes.length + usernameBytes.length + random.length)
                .put(pcNameBytes)
                .put(usernameBytes)
                .put(random).array();
        AES.setKey(key);
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

    private String loadFromMsgFile(String msgFile, String msg, stringseq seq) {
        try {
            return session.GetMessageContents(msgFile, msg, seq);
        } catch (jxthrowable e) {
            return null;
        }
    }

    private String loadFromPathMsgFile(String msg) {
        return loadFromMsgFile(pathMsgFile, msg, null);
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


    public Session getSession() {
        return session;
    }

    public Utilities getUtils() {
        return utils;
    }

    public File getPublicKeyFile() {
        return publicKeyFile;
    }

    public static String getCommandsMsgFile() {
        return commandsMsgFile;
    }

    public File getAppDataUletFile() {
        return appDataUletFile;
    }

    public CRUD getCrud() {
        return crud;
    }

    public AppBuilder getAppBuilder() {
        return appBuilder;
    }
}
