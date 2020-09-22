package sk.juvius.ulet;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;
import sk.juvius.ulet.util.Utilities;

import java.io.File;

public final class GlobalContainer {

    public static final String APP_NAME = "Ulet";

    private final Session session;
    private final ClassLoader defaultClassLoader;
    private final Utilities utils;

    GlobalContainer(Session session, ClassLoader defaultClassLoader) throws jxthrowable {
        this.session = session;
        this.defaultClassLoader = defaultClassLoader;
        this.utils = new Utilities(session);
    }

    public Session getSession() {
        return session;
    }

    public ClassLoader getDefaultClassLoader() {
        return defaultClassLoader;
    }

    public File getRootPath() {
        return new File(defaultClassLoader.getResource("").getPath()).getParentFile();
    }

    public Utilities getUtils() {
        return utils;
    }
}
