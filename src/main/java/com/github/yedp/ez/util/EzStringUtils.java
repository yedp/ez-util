package com.github.yedp.ez.util;


import java.io.UnsupportedEncodingException;

public class EzStringUtils {
    private static final String CHARSET = "UTF-8";
    public static final String SPACE = " ";
    public static final String EMPTY = "";

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * String长度确保不超长
     *
     * @param str
     * @param len
     * @return
     */
    public static String lengthEnsure(String str, int len) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }


    /**
     * 获取前半段，或者前半段指定长度
     *
     * @param content
     * @param maxLength
     * @return
     */
    public static String getPreHalf(String content, int maxLength) {
        if (isEmpty(content)) {
            return EMPTY;
        }
        String preStr = content.substring(0, (content.length() + 1) / 2);

        return lengthEnsure(preStr, maxLength);
    }

    public static byte[] strToBytes(String data) throws UnsupportedEncodingException {
        return strToBytes(data, CHARSET);
    }

    public static byte[] strToBytes(String data, String charset) throws UnsupportedEncodingException {
        return data.getBytes(charset);
    }

    public static String bytesToStr(byte[] bytes) throws UnsupportedEncodingException {
        return bytesToStr(bytes, CHARSET);
    }

    public static String bytesToStr(byte[] bytes, String charset) throws UnsupportedEncodingException {
        return new String(bytes, charset);
    }
}
