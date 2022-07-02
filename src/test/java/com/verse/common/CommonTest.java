package com.verse.common;


import com.auth0.jwt.interfaces.DecodedJWT;
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

//        String caicai = JwtTokenUtils.createToken("caicai", 30);
//        System.out.println(caicai);
//        JwtTokenUtils.verify(caicai);
        DecodedJWT verify = JwtTokenUtils.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJleHAiOjE2NTY0ODEzMTMsInVzZXJuYW1lIjoiY2FpY2FpIn0.jeQiZDsSarjWudIipk0kXZpk-vIkNoL7no-jcEz_pEYIh3w5R4-RYk1-HJOedVyhG4VXF8tbb1g47vgo5MMCcddR2XSN0cfcSiLT7gLE1kk8F5HZUTyQvJmwmlC4jT6gfhiIZJmpANCQA8tsAn6lOkeigTjW38ejxFwPrTIBy1VhKSm9xW-xWiVmJ8Ip2iQ4WSOU-c5ycVgDTWmI2736LmRVVmeIqhbBrqYNZXpEwcw0ZvkTGDcnY-pTxLM3ldWhJEH7pftYrbUtkrWjy1030alKhnp1CPXKTLehPMkhnKLk4tx8-zE4ibCUu5X9RvSI1jtZ1xr01zez0Y32IhjfGw");
        System.out.println(verify);
    }


}
