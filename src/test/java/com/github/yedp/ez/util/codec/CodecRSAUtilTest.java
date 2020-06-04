package com.github.yedp.ez.util.codec;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class CodecRSAUtilTest {
    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJqoqmOSXC6hfH8nKQgIxdXG6h+vbyY171gY5rOFIT6R/fJIFxLTij9EcpWtCQjn6dKMTGd/9esHryKCBJxTO1RalLsCj/nFE+ciJXDh4LAILSFU53d+u7MCe6q/OeV/KwBI9h4XzfaGlR+uj3eqniqzU6yy70qTmmhzTagkcFIQIDAQAB";
    String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMmqiqY5JcLqF8fycpCAjF1cbqH69vJjXvWBjms4UhPpH98kgXEtOKP0Ryla0JCOfp0oxMZ3/16wevIoIEnFM7VFqUuwKP+cUT5yIlcOHgsAgtIVTnd367swJ7qr855X8rAEj2HhfN9oaVH66Pd6qeKrNTrLLvSpOaaHNNqCRwUhAgMBAAECgYAq45xNMcY4J/UazxNSbCRuDqRwyqEOd9fQOteBMh8yoNfoaykkWsu7As7HmsrUk5mV7v+6ffbPupTLP4FubwRJ3nGqqaQONKW+V+HVL7/8/6cBXbRjsQwf2DziQb6ZiAN6t2HoyJc5H9RUQDQZfzbfPp3jUtSfmBrKJ3WJTXqp6QJBAOmlkiDiYsEZYKW3u6x1S57L/9QUmUiJnPd3+8ngB01rUj6+NbbnNFMvlE+mgUPc7mn2Zx0hjUU+cydGporV+XsCQQDc9bV1SqgCtVT4h+axrmQROsnK+8L5t9bNvXmgtffTAUX9a8iIxVFSeKo6E0z5aT/TiVyJuSotm5O5RgUvdjMTAkB+VVRKC56yInKrqEkF2vZkipFtryWinyYMeQfgBA5uwhTacCbDGwij4hojZziF5TPUf7MJgVbNlhLVrdHnSAN5AkB/dsa86NpO5agkztEIAt/7CHzMObvaLnEdsoXtbcuOz5c+f+To8VreRnl8kxG+dza4ou7dDBIGyU7IfFoVbQw9AkEAuF3lTYOc5SYRN/XGkRLu5VM94ah3mYiAPPdr+fglqqy0aBQJqRx0DtTzFWGBaPUg/4cqjx77oouzbEXg1udJsQ==";
    String charset = "UTF-8";

    @Test
    public void printKeyMapTest() {
        CodecRSAUtil.getInstance().printNewRsaKey("test");
    }

    @Test
    public void signAndVerifyTest() {
        String text = "hello world";
        String signStr = CodecRSAUtil.getInstance().sign(text, privateKey, charset);
        System.out.println(signStr);
        boolean verifyRs = CodecRSAUtil.getInstance().verify(text,signStr,publicKey,charset);
        System.out.println(verifyRs);
    }

    @Test
    public void encryptAndDecryptTest() {
        String text = "hello world,my name is ok!";
        String encryptStr = CodecRSAUtil.getInstance().encryptByPublicKey(text, publicKey, charset);
        System.out.println(encryptStr);
        String  decryptStr = CodecRSAUtil.getInstance().decryptByPrivateKey(encryptStr,privateKey,charset);
        System.out.println(decryptStr);
    }
}
