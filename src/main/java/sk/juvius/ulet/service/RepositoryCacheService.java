package sk.juvius.ulet.service;

import sk.juvius.ulet.core.cache.ContentCache;

public interface RepositoryCacheService extends ContentCache {
    int getRepoId();
}
