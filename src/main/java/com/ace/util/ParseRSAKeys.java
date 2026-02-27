package com.ace.util;

import org.springframework.util.StringUtils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * This source grab from
 * https://gist.github.com/destan/b708d11bd4f403506d6d5bb5fe6a82c5
 */
public final class ParseRSAKeys {

    public static Key parsePrivateKey(final String content) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (!StringUtils.hasText(content))
            throw new InvalidKeySpecException("key is null or empty");
        String keyContent = content + "";
        keyContent = keyContent
                .replaceAll("\\n", "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyContent));
        return kf.generatePrivate(keySpecPKCS8);
    }

    public static Key parsePublicKey(final String content) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (!StringUtils.hasText(content))
            throw new InvalidKeySpecException("key is null or empty");
        String keyContent = content + "";
        keyContent = keyContent
                .replaceAll("\\n", "")
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(keyContent));
        return kf.generatePublic(keySpecX509);
    }
}
