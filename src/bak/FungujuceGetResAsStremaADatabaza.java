package sk.juvius.ulet;

import com.ptc.pfc.pfcGlobal.pfcGlobal;
import sk.juvius.ulet.db.Crud;
import sk.juvius.ulet.db.DBManager;
import sk.juvius.ulet.db.impl.MySQLManager;
import sk.juvius.ulet.commands.login.model.User;
import sk.juvius.ulet.commands.login.service.UserService;
import sk.juvius.ulet.ui.Dialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().test();
    }

    void test() throws Exception {
        Dialog dialog = new Dialog();
        Image img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("image/asm.png"));
        dialog.setIconImage(img);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea(sql());
        panel.add(textArea);
        panel.setBackground(Color.white);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }

    static String sql() throws Exception {
        DBManager dbManager = new MySQLManager("10.2.0.26", 3306, "ulet", "ulet", "avsys1788");
        Crud crud = new Crud(dbManager);
        UserService us = new UserService(crud);
        User user = us.getUserByName("cervikl");
        return Boolean.toString(us.verify(user, "Cervik1788"));
    }

    public static void start() {
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
