package sk.juvius.ulet.commands.login;

import org.apache.commons.io.FileUtils;
import sk.juvius.ulet.crypto.AES;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class LoginCache {

    private final File cacheFile;
    private final Map<String, String[]> records = new HashMap<>();
    private final String itemSeparator = "\t";
    private final String rowSeparator = "\n";
    private String lastLoggedIn;

    public LoginCache(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    private void loadContent() throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(cacheFile);
        String content = AES.decrypt(bytes);
        if(content == null) return;
        String[] rows = content.split(rowSeparator);
        for(int i = 0; i < rows.length; i++) {
            String row = rows[i];
            String[] items = row.split(itemSeparator);
            if(i == 0 && items.length == 1) {
                lastLoggedIn = row;
            } else {
                records.put(items[0], items);
            }
        }
    }

    public void load() {
        if(exist()) {
            try {
                loadContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login cache file does not exist.");
        }
    }

    public void write() throws IOException {
        StringBuilder builder = new StringBuilder();
        if(lastLoggedIn != null) builder.append(lastLoggedIn).append(rowSeparator);
        for (String[] items : records.values()) {
            builder.append(items[0])
                    .append(itemSeparator)
                    .append(items[1])
                    .append(itemSeparator)
                    .append(items[2])
                    .append(rowSeparator);
        }
        builder.deleteCharAt(builder.length() - 1);
        byte[] bytes = AES.encrypt(builder.toString());
        if(bytes == null) return;
        FileUtils.writeByteArrayToFile(cacheFile, bytes);
    }

    public void delete() throws IOException {
        FileUtils.forceDelete(cacheFile);
    }

    public void setLastLoggedIn(String lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public void createRecord(String username, char[] password, boolean autoLogin) {
        records.put(username, new String[] {username, new String(password), Boolean.toString(autoLogin)});
    }

    public void removeRecord(String username) {
        records.remove(username);
    }

    public boolean exist() {
        return cacheFile.exists();
    }

    public boolean hasUser(String userName) {
        return records.containsKey(userName);
    }

    public char[] getUserPassword(String username) {
        return records.get(username)[1].toCharArray();
    }

    public boolean getAutoLogin(String username) {
        String[] arr = records.get(username);
        if(arr == null) return false;
        return Boolean.parseBoolean(arr[2]);
    }

    public String getLastLoggedIn() {
        return lastLoggedIn;
    }

    public List<String[]> getAllRecords() {
        return new ArrayList<>(records.values());
    }
}
