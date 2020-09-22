package sk.juvius.ulet.logging;

import java.io.*;

public class FileAppender implements Appender {

    private final File file;
    private BufferedOutputStream out;
    private boolean initialized;

    public FileAppender(File file) {
        this.file = file;
        if(file == null) {
            initialized = false;
            return;
        }
        try {
            boolean fileExist = file.exists();
            if(!fileExist) fileExist = file.createNewFile();
            if(!fileExist) throw new FileNotFoundException("Cannot create file.");
            this.out = new BufferedOutputStream(new FileOutputStream(file, true));
            initialized = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            initialized = false;
        }
    }

    @Override
    public void append(String formattedMsg) {
        try {
            formattedMsg = formattedMsg + "\n";
            out.write(formattedMsg.getBytes());
            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public File getFile() {
        return file;
    }

    public boolean isInitialized() {
        return initialized;
    }
}
