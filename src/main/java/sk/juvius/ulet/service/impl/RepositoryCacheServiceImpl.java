package sk.juvius.ulet.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.juvius.ulet.core.cache.SimpleCache;
import sk.juvius.ulet.crypto.SHA1;
import sk.juvius.ulet.service.LoginService;
import sk.juvius.ulet.service.RepositoryCacheService;

import java.io.File;
import java.io.IOException;

public class RepositoryCacheServiceImpl extends SimpleCache implements RepositoryCacheService {

    private static final Logger log = LoggerFactory.getLogger(RepositoryCacheServiceImpl.class);

    private final byte[] cacheRandom = new byte[]{-23,1,3,85,0,-96,127,10};
    private final LoginService loginService;

    public RepositoryCacheServiceImpl(File cacheFilePath, LoginService loginService) {
        super(cacheFilePath, log);
        this.loginService = loginService;
    }

    @Override
    public void load() {
        initializeFile();
        super.load();
    }

    @Override
    public void write() throws IOException {
        initializeFile();
        super.write();
    }

    @Override
    public void delete() throws IOException {
        initializeFile();
        super.delete();
    }

    @Override
    public boolean exist() {
        initializeFile();
        return super.exist();
    }

    private void initializeFile() {
        String path = cacheFile.isDirectory() ? cacheFile.getAbsolutePath() : cacheFile.getParent();
        byte[] toHash = ArrayUtils.addAll(loginService.getLoggedUser().getName().getBytes(), cacheRandom);
        String fileName = SHA1.hashHex(toHash) + ".repo";
        cacheFile = new File(path, fileName);
    }

    @Override
    public int getRepoId() {
        String content = getContent();
        int id = -1;
        try {
            id = Integer.parseInt(content);
        } catch (NumberFormatException e) {
            log.warn("Cannot read cached repo id " + e.getMessage());
        }
        return id;
    }
}
