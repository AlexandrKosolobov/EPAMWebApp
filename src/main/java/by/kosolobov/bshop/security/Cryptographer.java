package by.kosolobov.bshop.security;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class Cryptographer {
    private static final Logger log = LogManager.getLogger();
    private static Cipher cipher = null;

    static {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            log.log(Level.ERROR, "Cipher algorithm not found: {}", e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            log.log(Level.ERROR, "Cipher padding not found: {}", e.getMessage(), e);
        }
    }

    public static String encrypt(String toEnc, int id) {
        //todo
        return null;
    }

    public static String decrypt(String toDec, int id) {
        //todo
        return null;
    }
}
