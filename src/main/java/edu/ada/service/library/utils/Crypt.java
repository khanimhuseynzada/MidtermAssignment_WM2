package edu.ada.service.library.utils;

import java.util.Base64;

public class Crypt {
    public static String encrypt(String plain) {
        String b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());

        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char)(reverse.charAt(i) + OFFSET));
        }

        return tmp.toString();
    }
}
