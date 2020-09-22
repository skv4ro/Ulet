package sk.juvius.ulet;

import com.ptc.pfc.pfcGlobal.pfcGlobal;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(AppContext.APP_NAME);
    }

    public static void start() {
        AppContext.stupidDebug(new File("c:/users/skvarkaj/desktop/test.txt"));
        //JOptionPane.showMessageDialog(null, "WAITING FOR ATTACHING A DEBUGGER...");
        try {
            final AppContext appContext = AppContext.getInstance();
            if(!Verifier.verifySignature(appContext.getPublicKeyFile())) {
                appContext.getUtils().msgError("AVS-Utils: Wrong public key!");
                AppContext.getLogger().error("Wrong public key: " + appContext.getPublicKeyFile());
                return;
            }
            appContext.getLoginService().autoLogin();
            new AppBuilder(appContext).buildApp();
            appContext.msgManager.displaySuccessStartMsg();
            AppContext.getLogger().info("AVS-Utils has started successfully");
        } catch (Exception e) {
            sk.juvius.ulet.util.Utilities.stackTraceToSwingMsg(e);
        }
    }

    public static void startd() {
        try {
            //JOptionPane.showMessageDialog(null, "WAITING FOR ATTACHING A DEBUGGER...");
            main(new String[]{});
            pfcGlobal.GetProESession().RunMacro("~ Command `ProCmdExit`; ~ Activate `UI Message Dialog` `yes`");
        } catch (Exception e) {
            StringWriter out = new StringWriter();
            PrintWriter write = new PrintWriter(out);
            e.printStackTrace(write);
            JOptionPane.showMessageDialog(null, out.toString());
            try {
                pfcGlobal.GetProESession().RunMacro("~ Command `ProCmdExit`; ~ Activate `UI Message Dialog` `yes`");
            } catch (Exception ex) {
                out = new StringWriter();
                write = new PrintWriter(out);
                ex.printStackTrace(write);
                JOptionPane.showMessageDialog(null, out.toString());
            }
        }
    }

    public static void stop() {}
}
