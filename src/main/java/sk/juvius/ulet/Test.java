package sk.juvius.ulet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.crypto.AES;
import sk.juvius.ulet.db.*;
import sk.juvius.ulet.db.impl.MySQLManager;
import sk.juvius.ulet.db.impl.SQLiteManager;
import sk.juvius.ulet.model.Repository;
import sk.juvius.ulet.model.UploadItem;
import sk.juvius.ulet.ui.table.DynamicTableModel;
import sk.juvius.ulet.model.User;
import sk.juvius.ulet.ui.table.ColumnInfo;
import sk.juvius.ulet.view.UploadView;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        /*AES.setKey("key");
        File cacheFile = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Juvius\\Ulet", "login_data");
        LoginCache cache = new LoginCache(cacheFile);
        cache.setLastLoggedIn("olesm");
        cache.createRecord("olesm", "Oles1788".toCharArray(), true);
        cache.createRecord("skvarkaj", "pass2".toCharArray(), false);
        cache.write();
        cache.load();*/

        DBManager dbManager = new MySQLManager("10.2.0.26", 3306, "ulet", "ulet", "avsys1788");
        DBManager dbManager1 = new SQLiteManager("db.db");
        CRUD crud = new CRUD(dbManager);
        CRUD crudLite = new CRUD(dbManager1);

        /*UserService userService = new UserService(crud);
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

        /*setAesKey();
        File cacheFile = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Juvius\\Ulet", "login_data");
        LoginCache cache = new LoginCache(cacheFile);
        cache.load();
        for(String[] s : cache.getAllRecords()) {
            System.out.println(Arrays.toString(s));
        }*/

        /*RepositoryService rs = new RepositoryServiceImpl(crud, new TestLs());
        List<Repository> list = rs.getAllUserRepositories();

        RepositoryView rw = new RepositoryView();
        rw.setRepoList(list);
        rw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        rw.setResizable(true);
        RepositoryController rc = new RepositoryController(rw, rs);
        rw.setUserLabel(rs.getUser().getName());
        rc.search("RP25");
        rw.setVisible(true);*/

        /*Object[][] rowData = {
                { new Color(100,200,50), "sag", Boolean.TRUE, new ImageIcon(i1) },
                { new User(5, "skvarkaj", "5"), 5, Boolean.TRUE, new ImageIcon(i2) },
                { "Hello", "kg", Boolean.TRUE, new ImageIcon(R.getImage("prt.png")) },
                { 'c', 5, Boolean.TRUE, new ImageIcon(R.getImage("prt.png"))} };
        Object[] columnNames = { "A", "B", "C", "D" };
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 2) return Boolean.class;
                if(columnIndex == 3) return ImageIcon.class;
                return super.getColumnClass(columnIndex);
            }
        };*/
        /*ColumnModel col = new ColumnModel();
//        TableColumnModel col = new DefaultTableColumnModel();
        col.addColumn(new Column("Ahoj", String.class, 0));
        col.addColumn(new Column("Upload path", String.class, 1));
        col.addColumn(new Column("User name", User.class, 2));
        col.addColumn(new Column("Repo name", Repository.class, 3));
        col.addColumn(new Column("Upload image", Image.class, 4));*/


        /*JPopupMenu pop = new JPopupMenu();
        JMenuItem menuitem = new JMenuItem("ahoj");
        menuitem.setIcon(new ImageIcon(R.getImage("add.png").getScaledInstance(12,12,Image.SCALE_DEFAULT)));
        pop.add(menuitem);
        pop.add(new JMenuItem("svet"));

        JMenuItem menuitem2 = new JMenuItem("oakfapo");
        menuitem2.setIcon(new ImageIcon(R.getImage("remove.png").getScaledInstance(12,12,Image.SCALE_DEFAULT)));


        uw.getItemsTable().setComponentPopupMenu(pop);
        pop.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                pop.removeAll();
                if(uw.getItemsTable().getSelectedRows().length < 2) {
                    pop.add(menuitem);
                } else {
                    pop.add(menuitem2);
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });*/

        /*setAesKey();
        LoginCache cache = new LoginCache(new File("c:/users/skvarkaj/desktop/test.txt"));
        cache.load();
        char[] pass = cache.getUserPassword("lolo");
        if(pass != null) System.out.println(pass);
        cache.createRecord("lolo", "lama".toCharArray(), false);
        cache.write();*/

        /*String fileName2 = SHA1.hashHex("strakaf halabala");
        RepositoryCache cache2 = new RepositoryCache(new File("c:/users/skvarkaj/desktop", fileName2 + ".repo"));
        cache2.setContent("Ahoj straka");
        cache2.write();
        cache2.load();
        System.out.println(cache2);*/
    }

    private static void setAesKey() {
        String pcName = System.getenv("COMPUTERNAME");
        String username = System.getProperty("user.name");
        byte[] random = new byte[]{-12, 13, 95, 121, 18, -62, 35, 1};
        byte[] pcNameBytes = pcName.getBytes();
        byte[] usernameBytes = username.getBytes();
        byte[] key = ByteBuffer.allocate(pcNameBytes.length + usernameBytes.length + random.length)
                .put(pcNameBytes)
                .put(usernameBytes)
                .put(random).array();
        AES.setKey(key);
    }
}
