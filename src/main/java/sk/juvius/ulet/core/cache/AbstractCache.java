package sk.juvius.ulet.core.cache;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import sk.juvius.ulet.crypto.AES;

import java.io.File;
import java.io.IOException;

public abstract class AbstractCache implements CacheFile {

    protected final Logger log;
    protected File cacheFile;
    protected String content;

    public AbstractCache(File cacheFile, Logger log) {
        this.cacheFile = cacheFile;
        this.log = log;
    }

    @Override
    public void load() {
        if(exist()) {
            try {
                loadContent();
                log.info("Cache file " + cacheFile + " hase been successfully loaded.");
            } catch (IOException e) {
                log.error("Unable to load cache file " + cacheFile + " due to: " + e.getMessage());
            }
        } else {
            log.info("Cache file " + cacheFile + " does not exist.");
        }
    }

    @Override
    public void write() throws IOException {
        byte[] bytes = AES.encrypt(content);
        if(bytes == null) return;
        FileUtils.writeByteArrayToFile(cacheFile, bytes);
        log.info("Cache file " + cacheFile + " has been successfully written.");
    }

    @Override
    public void delete() throws IOException {
        FileUtils.forceDelete(cacheFile);
        log.info("Cache file " + cacheFile + " hase been successfully deleted.");
    }

    @Override
    public boolean exist() {
        return cacheFile.exists();
    }

    protected void loadContent() throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(cacheFile);
        content = AES.decrypt(bytes);
        if(content == null) {
            log.warn("Unable to decrypt login catch. Deleting cache file.");
            delete();
        }
    }
}
