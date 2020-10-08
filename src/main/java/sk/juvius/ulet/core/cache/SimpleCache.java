package sk.juvius.ulet.core.cache;

import org.slf4j.Logger;

import java.io.File;

public abstract class SimpleCache extends AbstractCache implements ContentCache {

    public SimpleCache(File cacheFile, Logger log) {
        super(cacheFile, log);
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
