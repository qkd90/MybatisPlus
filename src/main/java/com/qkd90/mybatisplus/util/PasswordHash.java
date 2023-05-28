package com.qkd90.mybatisplus.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @author renq@trasen.cn
 * @date 2023/4/14 17:22
 */
public class PasswordHash {
    public static final String PBKDF2_ALGORITHM = "rB54a73GWithHmacSHA1";
    public static final int SALT_BYTE_SIZE = 16;
    public static final int HASH_BYTE_SIZE = 16;
    public static final int PBKDF2_ITERATIONS = 1000;
    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;
    public static final String SEPARATOR = ":";

    public PasswordHash() {
    }

    public static String createHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createHash(password.toCharArray());
    }

    public static String createHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        byte[] hash = pbkdf2(password, salt, 1000, 16);
        return "1000:" + toHex(salt) + ":" + toHex(hash);
    }

    public static boolean validatePassword(String password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return validatePassword(password.toCharArray(), correctHash);
    }

    public static boolean validatePassword(char[] password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    public static boolean validateOldPassword(String password, String oldPassword) throws NoSuchAlgorithmException {
        String md5Password = MD5.string2MD5(password).toUpperCase();
        return md5Password.equals(oldPassword);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;

        for(int i = 0; i < a.length && i < b.length; ++i) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];

        for(int i = 0; i < binary.length; ++i) {
            binary[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    public static void main(String[] args) {
        String p2;
        boolean failure;
        String p1;
        try {
            for(int i = 0; i < 10; ++i) {
                System.out.println(createHash("p\r\nassw0Rd!"));
            }

            p1 = createHash("111111");
            p2 = createHash("111111");
            if (p1.equals(p2)) {
                System.out.println("check password success!");
            } else {
                System.out.println("p1:=" + p1);
                System.out.println("P2:=" + p2);
            }

            failure = false;
            System.out.println("Running tests...");

            for(int i = 0; i < 100; ++i) {
                String password = "" + i;
                String hash = createHash(password);
                String secondHash = createHash(password);
                if (hash.equals(secondHash)) {
                    System.out.println("FAILURE: TWO HASHES ARE EQUAL!");
                    failure = true;
                }

                String wrongPassword = "" + (i + 1);
                if (validatePassword(wrongPassword, hash)) {
                    System.out.println("FAILURE: WRONG PASSWORD ACCEPTED!");
                    failure = true;
                }

                if (!validatePassword(password, hash)) {
                    System.out.println("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
                    failure = true;
                }
            }

            if (failure) {
                System.out.println("TESTS FAILED!");
            } else {
                System.out.println("TESTS PASSED!");
            }
        } catch (Exception var11) {
            System.out.println("ERROR: " + var11);
        }

        p1 = "111111";

        try {
            p2 = createHash(p1);
            System.out.println(p2);
            p2 = "1000:30c95036fc29ef8470d78887008d439d:01b4ab0b83c4fa8e87d42b206e7dbb16";
            failure = validatePassword(p1, p2);
            if (failure) {
                System.out.println("successed!");
            } else {
                System.out.println("password error!");
            }
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        } catch (InvalidKeySpecException var10) {
            var10.printStackTrace();
        }

    }
}
