package by.kosolobov.barbershop.util.security;

import by.kosolobov.barbershop.util.Cryptographer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

import java.security.Provider;
import java.security.Security;

import static org.junit.jupiter.api.Assertions.*;

class CryptographerTest {

    @Test
    void encryptDecrypt() {
        String expected = "qwerty1234";
        String encrypt = Cryptographer.encrypt(expected, 5);
        String decrypt = Cryptographer.decrypt(encrypt, 5);

        LogManager.getLogger().log(Level.TRACE, "Test data: expected - {} || encrypted - {} || decrypted - {}", expected, encrypt, decrypt);

        assertEquals(expected, decrypt);
        assertNotEquals(expected, encrypt);
    }

    @Test
    void ListOfProviders() {
        for (Provider provider : Security.getProviders()) {
            System.out.println(provider);
        }
    }
}