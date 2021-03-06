package com.github.yedp.ez.util.codec;

import org.junit.Before;
import org.junit.Test;

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
    }


    @Test
    public void signAndVerifyTest() {
        String text = "hello world";
        String signStr = CodecRSAUtil.getInstance().sign(text, privateKey, charset);
        boolean verifyRs = CodecRSAUtil.getInstance().verify(text, signStr, publicKey, charset);
    }

    @Test
    public void encryptAndDecryptTest() {
        String text = "hello world,my name is ok!";
        String encryptStr = CodecRSAUtil.getInstance().encryptByPublicKey(text, publicKey, charset);
        String decryptStr = CodecRSAUtil.getInstance().decryptByPrivateKey(encryptStr, privateKey, charset);
    }
}
