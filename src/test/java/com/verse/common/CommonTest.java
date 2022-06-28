package com.verse.common;


import com.verse.common.util.JwtTokenUtils;

import java.security.NoSuchAlgorithmException;

public class CommonTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        KeyPair rsaKeyPair = JwtTokenUtils.getRSAKeyPair();
//        RSAPrivateKey privateKey = (RSAPrivateKey) rsaKeyPair.getPrivate();
//        RSAPublicKey publicKey = (RSAPublicKey) rsaKeyPair.getPublic();
//        String s = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//        String s2 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        System.out.println(s);
//        System.out.println(s2);

        String caicai = JwtTokenUtils.createToken("caicai");

        JwtTokenUtils.verify(caicai);

    }


}
