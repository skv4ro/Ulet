package sk.juvius.ulet;

import sk.juvius.ulet.exceptions.RootNotInCPException;

import java.net.URL;

public class AppContext {

    private static final String root;
    private String dbPath;

    static {
        URL rootUrl = AppContext.class.getClassLoader().getResource("");
        if(rootUrl == null) root = null;
        else root = rootUrl.getFile();
    }

    public static String getRoot() {
        return root;
    }

    public String getDbPath() {
        return dbPath;
    }
}
