package sk.juvius.ulet.core.cache;

public interface ContentCache extends CacheFile {
    String getContent();
    void setContent(String content);
}
