package sk.juvius.ulet.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public final class SHA1 {

    public static String hashHex(String toHash) {
        return byteToHex(hash(toHash.getBytes(StandardCharsets.UTF_8)));
    }

    public static String hashHex(byte[] toHash) {
        return byteToHex(hash(toHash));
    }

    public static byte[] hash(byte[] toHash) {
        byte[] sha1 = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(toHash);
            sha1 = crypt.digest();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
