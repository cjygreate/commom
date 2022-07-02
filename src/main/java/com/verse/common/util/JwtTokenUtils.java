package com.verse.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class JwtTokenUtils {

    private static final int EXPIRE_SECOND;
    private static final String RSA_PUBLIC_KEY; //"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAscBfdluf9/ViJi3+1A1tix+YzFZ7heEdtP1MuvTKdKwi5dBK7+xa2RTRAG7Ii8jkHn3hF7x8BbTPzm6MDzDMPt+MRSTEJNJmL7dcYEVnPQkSnEB6lEv6DKbzJfbUaUOfN2XanWTjgAsEHOvq24JC8onVP15Z+1v6ze4dv/A1ubwLDhIXFvDcp3yM/M63YdU4/0xW9lpxvKkTRjovsuppa7kVuuZfaI56Nm0v9pWx6TMqpcTe7KT9ugy11WJLwXuw4YpjapZwFYaiNQ25Qln0DBkQ1UFtsp8Uh9e/c/b+RgfnzLHrZqb2s30fsp+pFoudFwtxH3YL5lpE7zRQTgRzwwIDAQAB";


    private static final String RSA_PRIVATE_KEY; //"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxwF92W5/39WImLf7UDW2LH5jMVnuF4R20/Uy69Mp0rCLl0Erv7FrZFNEAbsiLyOQefeEXvHwFtM/ObowPMMw+34xFJMQk0mYvt1xgRWc9CRKcQHqUS/oMpvMl9tRpQ583ZdqdZOOACwQc6+rbgkLyidU/Xln7W/rN7h2/8DW5vAsOEhcW8NynfIz8zrdh1Tj/TFb2WnG8qRNGOi+y6mlruRW65l9ojno2bS/2lbHpMyqlxN7spP26DLXVYkvBe7DhimNqlnAVhqI1DblCWfQMGRDVQW2ynxSH179z9v5GB+fMsetmpvazfR+yn6kWi50XC3EfdgvmWkTvNFBOBHPDAgMBAAECggEAa0CgWVI/2+X8Kv1l3Z2ck22lTOaB8e47dpHAUq6R2oRjKjfcf5gl2QZudcxixbWZr6xUvXXFf8vx96VMbX4MP4Ozp6TflTg+4FvLKWu3N2TaDd36GLEfV7ME08tzr3BWCZ8zofTWMNR0zt6q/3hB3Qa93VaZBMojM06SNNZEFKUXPVFqBTxvZRIjp36wJ0tP6tw1BhwxZPnWVomHBlNN0cSC+Tu6fDik31c5s7SlE7quuX1pMGhOW5kKf7bdlxlwA6aFhEwFgWKHstP/xR8Y54POYD38rNDlEaiBmUKrRfjCe1WYnVkXcxPuM4CggzGAMZk+0Rb8NmopOCPkrm0qQQKBgQDteFHup+FP5/H+yPPuZHFFQJaxxG4vxmXmTJSs8wydCzqKU5R4ogjSHIA7vW3XhBaD/oVCYC1JxShXwq2hb2WdzIrhwv88SWtlGPfLwSRNlby4tdD8ovIMrvuqZaPqgHL8e7q6ryMxmcFhGx5aK/u+864eX34A/SwrtUxVam9XlQKBgQC/nx7VyPqYs7kcQMl9SQimls3iSu4FkRhFkr1m4x7fP2KZTEsySUXKv8ikZTAvsPyGaaSV4cG4LZtAca41fqHc0xQPSH5Uzfh69u2Q8rCPyYFwl+1hTH5a9EXD8NQBiZUSVvkHeP4enoCKLv/o6oyH/ZmaxnT6gpgDJgmPkHJn9wKBgCPUI4h+i+AifsCSUTuGrBBmzlXD7VwH8xfsNgJiKWUIbKyyL5SD3hDVfAOHcSszIrKHlFOPb0XSGWQkW04gIJTOZ6XoZdoSucFnIvv7CB8smGzGAeIcM6j2PcJX04NuHes6P9Iq2oSBTkv4a3vvZuxIl9y9S6WajS+LCxlYFbSBAoGBAIq+T7lDWA8Qsks+raVFKDpWnqPm1Qi60fQuF21TrZq/bkjI8PlsDFiNHF3L1mPxpiY8f6r2Vylf/DVhGl4pXlFhk/is6KvGps+B0ulMj4LMyCgaGQqGNEbu70S8LwUd9Q3eY1mBxZ2t7iwfc7PVgANHH9ztLO3leONuuyVz1SJpAoGAfN2ZVVZhs1xNY74xTsnIqAUQ/Hxim88YFmrRCEDLUO+doQ4icgLBrBfbQQaYDsXswgC5o2JxN115TswkqS+srd3ZdIJLXGsPdI5Z4AKqDkH2yBMcR5F6II0H8EZgxswAJv0zzDpGTyrD/VDXXVCaRPCiOUP/DVfIrTHxZqfl/6o=";

    static {
        try {
            RSA_PRIVATE_KEY = VersePropertyUtils.getLocalProperty("jwt.private.key");
            RSA_PUBLIC_KEY = VersePropertyUtils.getLocalProperty("jwt.public.key");
            EXPIRE_SECOND = Integer.parseInt(VersePropertyUtils.getLocalProperty("jwt.expire.second"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createToken(String username, long second) throws NoSuchAlgorithmException {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(second);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String token = JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(Algorithm.RSA256(null, StringToPrivateKey(RSA_PRIVATE_KEY)));
        return token;
    }

    public static String createToken(String username) throws NoSuchAlgorithmException {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(60 * 60);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String token = JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(Algorithm.RSA256(null, StringToPrivateKey(RSA_PRIVATE_KEY)));
        return token;
    }

    public static DecodedJWT verify(String token) {
        DecodedJWT decode = JWT.decode(token);
        Algorithm algorithm = Algorithm.RSA256(StringToPublicKey(RSA_PUBLIC_KEY), null);
        algorithm.verify(decode);
        return decode;
    }

    public static KeyPair getRSAKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    public static RSAPublicKey StringToPublicKey(String publicKey) {
        KeyFactory keyFactory = null;
        PublicKey publicKeyPair = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec pkcs8EncodedKeySpec = new X509EncodedKeySpec(bytes);
            publicKeyPair = keyFactory.generatePublic(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return (RSAPublicKey) publicKeyPair;

    }

    public static RSAPrivateKey StringToPrivateKey(String privateKey) {
        KeyFactory keyFactory = null;
        PrivateKey privateKeyPair = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
            privateKeyPair = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return (RSAPrivateKey) privateKeyPair;
    }



}
