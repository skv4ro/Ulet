package sk.juvius.ulet.core.cache;


import java.io.IOException;

public interface CacheFile {
    void load();
    void write() throws IOException;
    void delete() throws IOException;
    boolean exist();
}
