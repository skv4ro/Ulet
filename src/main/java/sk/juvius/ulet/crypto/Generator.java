package sk.juvius.ulet.crypto;

import org.apache.commons.io.FileUtils;
import sk.juvius.ulet.util.Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

public final class Generator {
    private static final File PATH = new File(System.getProperty("user.home") + "/desktop");
    private static final File SECRET_BYTE_ARRAY_FILE = new File(PATH, "secret.txt");
    private static final File PUBLIC_KEY_FILE = new File(PATH, "public.key");
    private static final File SIGNATURE_BYTE_ARRAY_FILE = new File(PATH, "signature.txt");
    static final String ALGORITHM = "RSA";
    static final String SIGN_ALGORITHM = "SHA256with" + ALGORITHM;

    public static void main(String[] args) {
        try {
            byte[] secret = new byte[64];
            SecureRandom.getInstanceStrong().nextBytes(secret);
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            Signature sign = Signature.getInstance(SIGN_ALGORITHM);
            sign.initSign(privateKey);
            sign.update(secret);
            byte[] signature = sign.sign();
            saveSecretToFile(secret);
            savePublicKeyToFile(publicKey);
            saveSignatureToFile(signature);
        } catch (Exception e) {
            Utilities.stackTraceToSwingMsg(e);
        }
    }

    private static void savePublicKeyToFile(PublicKey key) throws IOException {
        FileUtils.writeByteArrayToFile(PUBLIC_KEY_FILE, key.getEncoded());
    }

    private static String byteArrayToFieldString(byte[] array, String interFix) {
        String strArr = Arrays.toString(array);
        String prefix = "private static final byte[] " + interFix + " = ";
        strArr = strArr.replace("[", "{");
        strArr = strArr.replace("]", "}");
        return prefix + strArr + ";";
    }

    private static void saveSecretToFile(byte[] secret) throws IOException {
        String result = byteArrayToFieldString(secret, "SECRET");
        FileUtils.writeStringToFile(SECRET_BYTE_ARRAY_FILE, result, StandardCharsets.UTF_8);
    }

    private static void saveSignatureToFile(byte[] signature) throws IOException {
        String result = byteArrayToFieldString(signature, "SIGNATURE");
        FileUtils.writeStringToFile(SIGNATURE_BYTE_ARRAY_FILE, result, StandardCharsets.UTF_8);
    }
}
