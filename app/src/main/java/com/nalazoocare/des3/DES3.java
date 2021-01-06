package com.nalazoocare.des3;

import android.text.TextUtils;
import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-08-12
 */
public class DES3 {
    // Key
    //DES 는 16Byte
    //3DES 는 24Byte

    //ECB
    //CBC 방식 2가지가 있음
    private final static String secretKey = "timehub@2012##$$good$$##";
    // Vector
    private final static String iv = "";
    // Encryption and decryption unified encoding to use
    private final static String encoding = "utf-8";


    /**
     * 3DES encryption
     *
     * @return
     * @Param plainText plain text
     */


    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encodeToString(encryptData, Base64.DEFAULT);
    }

    /**
     * 3DES decryption
     *
     * @return
     * @Param encryptText encrypted text
     */
    public static String decode(String encryptText) {
        if (TextUtils.isEmpty(encryptText)) return "";
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            Key deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            byte[] decryptData = cipher.doFinal(Base64.decode(encryptText, Base64.DEFAULT));
            return new String(decryptData, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
