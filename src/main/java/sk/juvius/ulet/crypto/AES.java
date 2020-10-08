package sk.juvius.ulet.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static final Logger log = LoggerFactory.getLogger(AES.class);
    private static SecretKeySpec secretKey;

    public static void setKey(byte[] key) {
        try {
            secretKey = createKey(key);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
    }

    private static SecretKeySpec createKey(byte[] key) throws NoSuchAlgorithmException {
        MessageDigest sha;
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

    public static byte[] encrypt(String strToEncrypt, byte[] key) {
        try {
            SecretKeySpec spec;
            if(key == null) spec = secretKey;
            else spec = createKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            return cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.debug("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(byte[] toDecrypt, byte[] key) {
        try {
            SecretKeySpec spec;
            if(key == null) spec = secretKey;
            else spec = createKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, spec);
            return new String(cipher.doFinal(toDecrypt), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.debug("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static byte[] encrypt(String strToEncrypt) {
        return encrypt(strToEncrypt, null);
    }

    public static String decrypt(byte[] toDecrypt) {
        return decrypt(toDecrypt, null);
    }
}
