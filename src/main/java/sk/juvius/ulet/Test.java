package sk.juvius.ulet;

import sk.juvius.ulet.commands.login.LoginCache;
import sk.juvius.ulet.commands.login.controler.LoginController;
import sk.juvius.ulet.commands.login.controler.LogoutController;
import sk.juvius.ulet.commands.login.controler.RememberController;
import sk.juvius.ulet.commands.login.controler.UsernameController;
import sk.juvius.ulet.commands.login.service.LoginService;
import sk.juvius.ulet.commands.login.service.UserService;
import sk.juvius.ulet.commands.login.view.LoginView;
import sk.juvius.ulet.commands.login.view.LogoutView;
import sk.juvius.ulet.crypto.AES;
import sk.juvius.ulet.db.Crud;
import sk.juvius.ulet.db.DBManager;
import sk.juvius.ulet.db.impl.MySQLManager;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        /*AES.setKey("key");
        File cacheFile = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Juvius\\Ulet", "login_data");
        LoginCache cache = new LoginCache(cacheFile);
        cache.setLastLoggedIn("olesm");
        cache.createRecord("olesm", "Oles1788".toCharArray(), true);
        cache.createRecord("skvarkaj", "pass2".toCharArray(), false);
        cache.write();
        cache.load();

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        DBManager dbManager = new MySQLManager("10.2.0.26", 3306, "ulet", "ulet", "avsys1788");
        Crud crud = new Crud(dbManager);
        UserService userService = new UserService(crud);
        LoginService loginService = new LoginService(userService, cache);
        LoginView loginView = new LoginView();
        LogoutView logoutView = new LogoutView();
        LoginController loginController = new LoginController(loginView, loginService);
        RememberController rememberController = new RememberController(loginView);
        UsernameController usernameController = new UsernameController(loginView, cache);
        loginView.setUsernameTextFieldController(usernameController);
        loginView.setLoginController(loginController);
        loginView.setRememberController(rememberController);
        loginView.setVisible(true);
        /*LogoutController lc = new LogoutController(loginView, logoutView);
        logoutView.getUsernameLabel().setText("skvarkaj");
        logoutView.setLogoutController(lc);
        loginView.setVisible(true);*/

        /*String key = new String(new byte[]{1,2,56,95,127,1,32,48});
        String user = "skvarkaj";
        char[] password = "ahoj svet".toCharArray();
        boolean autologin = false;
        String toEnc = user + "\n"  + new String(password) + "\n" + autologin;
        System.out.println(key);
        AES.setKey(key);
        String enc = AES.encrypt(toEnc);
        System.out.println(enc);
        String dec = AES.decrypt(enc);
        System.out.println(dec);*/

        /*char[] pass = "Skvarka1788".toCharArray();
        SecureString sec = new SecureString(pass);
        String hash = Password.hash(sec).addRandomSalt(12).withSCrypt().getResult();
        User user = new User(10, "skvarkaj", hash);
        UserService us = new UserService(null);
        System.out.println(us.verify(user, new char[]{'a'}));*/

        /*AES.setKey("key");
        File cacheFile = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Juvius\\Ulet", "login_data");
        LoginCache cache = new LoginCache(cacheFile);
        cache.load();
        for(String[] s : cache.getAllRecords()) {
            System.out.println(Arrays.toString(s));
        }*/
    }
}
