package com.marsh.zutils.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 签名工具类
 *
 * @author lucky.zhou
 */
public class SignUtil {

    private static final String MD5 = "MD5";

    private SignUtil() {

    }

    public static String getMD5Hash(byte[] v, String salt) {
        MessageDigest md = getDigest(MD5);
        if (salt != null) {
            md.update(salt.getBytes());
        }
        md.update(v);
        byte[] d = md.digest();
        return ByteUtil.encode(d);
    }

    public static String getMD5Hash(byte[] v) {
        return getMD5Hash(v, null);
    }


    public static MessageDigest getDigest(String digest) {
        try {
            return MessageDigest.getInstance(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(String.format("The requested MessageDigest (%s) does not exist",
                    digest), e);
        }

    }
}
