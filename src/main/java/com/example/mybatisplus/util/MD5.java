package com.example.mybatisplus.util;

import java.security.MessageDigest;

/**
 * @author renq@trasen.cn
 * @date 2023/4/14 17:22
 */
public class MD5 {
    public MD5() {
    }

    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception var8) {
            System.out.println(var8.toString());
            var8.printStackTrace();
            return "";
        }

        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for(int i = 0; i < charArray.length; ++i) {
            byteArray[i] = (byte)charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();

        for(int i = 0; i < md5Bytes.length; ++i) {
            int val = md5Bytes[i] & 255;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();

        for(int i = 0; i < a.length; ++i) {
            a[i] = (char)(a[i] ^ 116);
        }

        String s = new String(a);
        return s;
    }

    public static void main(String[] args) {
        String s = new String("whuhtest");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s).toUpperCase());
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
        String ss = "E10ADC3949BA59ABBE56E057F20F883E";
        System.out.println("解密码：" + convertMD5(ss));
    }
}
