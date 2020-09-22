package sk.juvius.ulet;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

final class Verifier {
    private static final byte[] SIGNATURE = {-111, -119, 76, -1, -89, 76, 37, -108, -67, 41, -59, -17, 76, 116, 48, -7, 93, -63, 48, -6, -119, -33, 67, 75, -52, 35, -49, -55, -35, 43, -38, -85, 62, -75, -124, -116, 42, 96, -123, -10, 124, -119, -43, -70, 30, 97, 19, 50, -98, -95, 102, -119, -94, -71, 96, -92, 60, -15, 20, 86, 93, 49, -73, -112, -80, -96, 37, 38, -117, 10, -79, -47, 34, 16, 37, -92, -29, 22, -61, -97, 110, -56, -58, 4, 33, -87, -81, 26, -107, 19, 33, -104, -43, -76, -106, 63, -36, 19, 34, -105, -81, 50, -112, -61, 57, 99, 94, -51, 43, 98, -114, -6, -57, -73, 115, 3, 82, -108, -18, -11, 101, -102, -71, -117, 101, -47, -18, -127, -72, -67, -45, -104, -122, -45, 11, -104, 61, 57, 106, 102, 93, -94, 2, 20, 22, 39, -1, -46, 70, 39, 90, 43, -96, 9, -51, 7, -128, -106, 58, 97, 101, -94, -114, 46, -124, -104, 112, 38, -30, 70, 72, -98, 78, -101, 38, -10, -9, -100, 122, 37, -33, 92, 78, 53, 0, -51, 1, 18, 5, 5, -43, 6, 50, 21, -35, 42, 94, -120, -10, 19, 65, -44, 24, -66, -7, 22, -95, -27, -30, -12, -34, 107, -87, 113, -106, 51, -55, 124, -62, 57, -24, -66, -19, 116, -9, 47, 119, 57, 111, -86, -14, -52, 38, -104, 103, 15, -15, 18, 24, -92, 67, -98, -20, 34, 4, -36, 80, 94, -48, 23, -38, -71, 126, -90, -118, 117};
    private static final byte[] SECRET = {-34, -38, -19, -72, 96, -52, 23, -106, -20, -79, -119, -40, 12, -77, 4, 26, -41, -39, 43, -115, 119, 93, -79, 14, 69, 67, 38, -9, 84, -128, 102, 12, -4, -19, -115, -120, 11, 61, -113, -33, -125, 120, 78, 16, -55, 87, 78, -113, -93, -126, -127, -42, -113, 12, -126, 114, 114, -45, -61, 98, 19, -78, 111, 39};

    private Verifier(){}

    static boolean verifySignature(File keyFile) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, IOException {
        byte[] keyBytes = FileUtils.readFileToByteArray(keyFile);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(publicKey);
        sign.update(SECRET);
        return sign.verify(SIGNATURE);
    }
}
