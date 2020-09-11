package sk.juvius.ulet;

import com.ptc.pfc.pfcGlobal.pfcGlobal;
import sk.juvius.ulet.db.DBManager;
import sk.juvius.ulet.login.User;
import sk.juvius.ulet.login.UserService;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().test();
    }

    void test() throws Exception {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setAlwaysOnTop(true);
        Image img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("image/asm.png"));
        dialog.setIconImage(img);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        //dialog.setSize(200,100);
        JTextArea textArea = new JTextArea(sql());
        dialog.add(textArea);
        dialog.pack();
        dialog.setVisible(true);
    }

    static String sql() throws Exception {
        File file = new File("c:/users/skvarkaj/documents/ulet/db.db");
        UserService us = new UserService(new DBManager(file.getAbsolutePath()));
        StringBuilder sb = new StringBuilder();
        for(User u : us.getUsers()) {
            sb.append(u.getName()).append(" ").append(u.getPassword()).append("\n");
        }
        return sb.toString();
    }

    public static void start() {
        try {
            //JOptionPane.showMessageDialog(null, "WAITING...");
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
