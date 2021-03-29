package edu.ada.service.library.utils;

import java.security.SecureRandom;

public class Token {

    public static String generateRandomToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);

        return bytes.toString();
    }
}
