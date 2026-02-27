package com.ace.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Base52Codec {

    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final int BASE = ALPHABET.length;
    private static final Map<Character, Integer> LOOKUP = new HashMap<>(BASE);

    static {
        for (int i = 0; i < BASE; i++) {
            LOOKUP.put(ALPHABET[i], i);
        }
    }

    private Base52Codec() {
        // Utility class
    }

    public static String encode(String plainText) {
        if (plainText == null || plainText.isEmpty()) {
            return plainText;
        }

        byte[] inputBytes = plainText.getBytes(StandardCharsets.UTF_8);
        int leadingZeroCount = countLeadingZeros(inputBytes);

        BigInteger value = new BigInteger(1, inputBytes);
        StringBuilder encoded = new StringBuilder();

        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = value.divideAndRemainder(BigInteger.valueOf(BASE));
            encoded.append(ALPHABET[divRem[1].intValue()]);
            value = divRem[0];
        }

        for (int i = 0; i < leadingZeroCount; i++) {
            encoded.append(ALPHABET[0]);
        }

        return encoded.reverse().toString();
    }

    public static String decode(String encodedText) {
        if (encodedText == null || encodedText.isEmpty()) {
            return encodedText;
        }

        BigInteger value = BigInteger.ZERO;
        int leadingZeroCount = 0;

        for (int i = 0; i < encodedText.length(); i++) {
            char current = encodedText.charAt(i);
            Integer digit = LOOKUP.get(current);
            if (digit == null) {
                return encodedText;
            }
            if (digit == 0 && value.equals(BigInteger.ZERO)) {
                leadingZeroCount++;
            }
            value = value.multiply(BigInteger.valueOf(BASE)).add(BigInteger.valueOf(digit));
        }

        byte[] decodedBytes = value.toByteArray();
        if (decodedBytes.length > 0 && decodedBytes[0] == 0) {
            decodedBytes = Arrays.copyOfRange(decodedBytes, 1, decodedBytes.length);
        }

        if (leadingZeroCount > 0) {
            byte[] output = new byte[leadingZeroCount + decodedBytes.length];
            System.arraycopy(decodedBytes, 0, output, leadingZeroCount, decodedBytes.length);
            decodedBytes = output;
        }

        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    private static int countLeadingZeros(byte[] input) {
        int count = 0;
        for (byte b : input) {
            if (b == 0) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
