package com.example.roshan.rahiapp;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AESEncrypt {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";

    public static String encrypt(String value) throws Exception
    {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESEncrypt.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue = Base64.encodeToString(encryptedByteValue,Base64.DEFAULT);
        return encryptedValue;
    }

    public static String decrypt(String value) throws Exception
    {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESEncrypt.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] decyptedByteValue = Base64.decode(value.getBytes(),Base64.DEFAULT);
        byte[] decryptedBase64Value = cipher.doFinal(decyptedByteValue);
        String decryptedValue = new String(decryptedBase64Value,"utf-8");
        return decryptedValue;
    }
    private static Key generateKey()
    {
        Key key = new SecretKeySpec(AESEncrypt.KEY.getBytes(),AESEncrypt.ALGORITHM);
        return key;
    }
}
