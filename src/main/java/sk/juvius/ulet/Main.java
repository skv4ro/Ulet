package sk.juvius.ulet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        System.out.println(AppContext.APP_NAME);
    }

    public static void start() {
        //JOptionPane.showMessageDialog(null, "WAITING FOR ATTACHING A DEBUGGER...");
        log.info(AppContext.APP_NAME + " HAS STARTED");
        try {
            final AppContext appContext = AppContext.getInstance();
            if(!Verifier.verifySignature(appContext.getPublicKeyFile())) {
                appContext.getUtils().msgError("AVS-Utils: Wrong public key!");
                log.error("Wrong public key: " + appContext.getPublicKeyFile());
                return;
            }
            appContext.services.getLoginService().autoLogin();
            appContext.services.getRepositoryService().autoSelectRepo();
            appContext.getAppBuilder().buildApp();
            appContext.messages.displaySuccessStartMsg();
            log.info(AppContext.APP_NAME + " has started successfully");
        } catch (Exception e) {
            String stackTrace = sk.juvius.ulet.util.Utilities.stackTraceToString(e);
            sk.juvius.ulet.util.Utilities.msgSwing(stackTrace);
            log.error(stackTrace);
        }
    }

    /*public static void startd() {
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
    }*/

    public static void stop() {
        log.info(AppContext.APP_NAME + " HAS STOPPED");
    }
}
