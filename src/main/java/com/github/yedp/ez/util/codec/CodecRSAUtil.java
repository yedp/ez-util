package com.github.yedp.ez.util.codec;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yedp
 * @Date: 2020/06/03 17:42
 * @Description：加密、验签工具
 */
public class CodecRSAUtil {
    private CodecRSAUtil() {
    }

    private static class instanceHolder {
        static CodecRSAUtil instance = new CodecRSAUtil();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static CodecRSAUtil getInstance() {
        return instanceHolder.instance;
    }


    /*
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";
    /*
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    private final String KEY_ALGORITHM = "RSA";
    private final int MAX_ENCRYPT_BLOCK = 117;
    private final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 签名字符串
     *
     * @param text
     * @param privateKey
     * @param charset
     * @return
     */
    public String sign(String text, String privateKey, String charset) {
        return Base64.getEncoder().encodeToString(this.sign(this.convertContentToBytes(text, charset), privateKey));
    }


    /**
     * 签名字节数据
     *
     * @param data
     * @param privateKey
     * @return
     */
    public byte[] sign(byte[] data, String privateKey) {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateK);
            signature.update(data);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法参数错误！");
        } catch (SignatureException e) {
            throw new RuntimeException("签名异常，请检查秘钥是否正确！");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("秘钥无效，请检查秘钥是否正确！");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("秘钥与期望不一致，请检查秘钥是否正确！");
        }
    }

    /**
     * 验证签名字符串
     *
     * @param text
     * @param signatureText
     * @param publicKey
     * @param charset
     * @return
     */
    public boolean verify(String text, String signatureText, String publicKey, String charset) {
        return this.verify(this.convertContentToBytes(text, charset), Base64.getDecoder().decode(signatureText), publicKey);
    }

    /**
     * 验证签名字节数组
     *
     * @param data
     * @param signatureData
     * @param publicKey
     * @return
     */
    public boolean verify(byte[] data, byte[] signatureData, String publicKey) {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicK = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicK);
            signature.update(data);
            return signature.verify(signatureData);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法参数错误！");
        } catch (SignatureException e) {
            throw new RuntimeException("签名异常，请检查秘钥是否正确！");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("秘钥无效，请检查秘钥是否正确！");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("秘钥与期望不一致，请检查秘钥是否正确！");
        }
    }

    /**
     * 通过公钥加密字符串
     *
     * @param text
     * @param publicKey
     * @param charset
     * @return
     */
    public String encryptByPublicKey(String text, String publicKey, String charset) {
        return Base64.getEncoder().encodeToString(this.encryptByPublicKey(this.convertContentToBytes(text, charset), publicKey));
    }

    /**
     * 通过公钥加密字节流
     *
     * @param data
     * @param publicKey
     * @return
     */
    public byte[] encryptByPublicKey(byte[] data, String publicKey) {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(1, publicK);
            return getDoFinalData(data, cipher, MAX_ENCRYPT_BLOCK);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法参数错误！");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("秘钥无效，请检查秘钥是否正确！");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("秘钥与期望不一致，请检查秘钥是否正确！");
        } catch (BadPaddingException e) {
            throw new RuntimeException(e.getCause());
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("加密块size有问题！");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e.getCause());
        } catch (IOException e) {
            throw new RuntimeException("数据IO问题！");
        }
    }

    /**
     * 用私钥解密加密字符串
     *
     * @param text
     * @param privateKey
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public String decryptByPrivateKey(String text, String privateKey, String charset) {
        return this.convertBytesToContent(this.decryptByPrivateKey(Base64.getDecoder().decode(text), privateKey), charset);
    }


    /**
     * 用私钥解密加密字节串
     *
     * @param encryptedData
     * @param privateKey
     * @return
     */
    public byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        try {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(2, privateK);
            return getDoFinalData(encryptedData, cipher, MAX_DECRYPT_BLOCK);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("解密算法参数错误！");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("秘钥无效，请检查秘钥是否正确！");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("秘钥与期望不一致，请检查秘钥是否正确！");
        } catch (BadPaddingException e) {
            throw new RuntimeException(e.getCause());
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("解密块size有问题！");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e.getCause());
        } catch (IOException e) {
            throw new RuntimeException("数据IO问题！");
        }
    }

    private byte[] getDoFinalData(byte[] data, Cipher cipher, int maxBlock) throws BadPaddingException, IllegalBlockSizeException, IOException {
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        for (int i = 0; inputLen - offSet > 0; offSet = i * maxBlock) {
            byte[] cache;
            if (inputLen - offSet > maxBlock) {
                cache = cipher.doFinal(data, offSet, maxBlock);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            ++i;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 字符串转字节数组
     *
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private byte[] convertContentToBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 字节数组转字符串
     *
     * @param bytes
     * @param charset
     * @return
     */
    private String convertBytesToContent(byte[] bytes, String charset) {
        if (charset == null || "".equals(charset)) {
            return new String(bytes);
        }
        try {
            return new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public Map<String, Key> genKeyPair() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不存在");
        }
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Key> keyMap = new HashMap<String, Key>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public String getPrivateKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PRIVATE_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public String getPublicKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PUBLIC_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
