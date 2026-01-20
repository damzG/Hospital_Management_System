package util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    //crpyptographic salt for encoding and hashing
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

//    Method of hashing using PBKeySpec
    public static String hashPassword(char[] password, byte[] salt) {

        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, 10000, 256);
            SecretKeyFactory skf =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

