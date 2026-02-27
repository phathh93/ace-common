package com.ace.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String LATE_NUMBERS = "0123456789";
    private static final String FIRST_NUMBERS = "123456789";
    private static final ThreadLocal<SecureRandom> RANDOM = ThreadLocal.withInitial(SecureRandom::new);


    public static String hashSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String getRandomString(int length) {
        SecureRandom random = RANDOM.get();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomNumber(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        SecureRandom random = RANDOM.get();
        StringBuilder sb = new StringBuilder(length);
        sb.append(FIRST_NUMBERS.charAt(random.nextInt(FIRST_NUMBERS.length())));
        for (int i = 1; i < length; i++) {
            int index = random.nextInt(LATE_NUMBERS.length());
            sb.append(LATE_NUMBERS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(getRandomString(10));
        System.out.println(getRandomNumber(10));

        PhoneNumberUtil phoneUtils = PhoneNumberUtil.getInstance();
        try {
            String phoneRaw = "+840789545455";
            Phonenumber.PhoneNumber number = phoneUtils.parse(phoneRaw, null);
            System.out.println(phoneUtils.getRegionCodeForNumber(number));
            System.out.println(phoneUtils.format(number, PhoneNumberUtil.PhoneNumberFormat.E164));
        } catch (NumberParseException ex) {
            ex.printStackTrace();
        }
    }
}
