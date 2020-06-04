package com.github.yedp.ez.util.codec;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class CodecRSAUtilTest {
    String publicKey = null;
    String privateKey = null;
    String charset = "UTF-8";

    @Before
    public void generateKeys() {
        Map map = CodecRSAUtil.getInstance().genKeyPair();
        publicKey = CodecRSAUtil.getInstance().getPublicKey(map);
        privateKey = CodecRSAUtil.getInstance().getPrivateKey(map);
        System.out.println("publicKey:" + publicKey);
        System.out.println("privateKey:" + privateKey);
    }


    @Test
    public void signAndVerifyTest() {
        String text = "hello world";
        String signStr = CodecRSAUtil.getInstance().sign(text, privateKey, charset);
        System.out.println(signStr);
        boolean verifyRs = CodecRSAUtil.getInstance().verify(text, signStr, publicKey, charset);
        System.out.println(verifyRs);
    }

    @Test
    public void encryptAndDecryptTest() {
        String text = "hello world,my name is ok!";
        String encryptStr = CodecRSAUtil.getInstance().encryptByPublicKey(text, publicKey, charset);
        System.out.println(encryptStr);
        String decryptStr = CodecRSAUtil.getInstance().decryptByPrivateKey(encryptStr, privateKey, charset);
        System.out.println(decryptStr);
    }
}
