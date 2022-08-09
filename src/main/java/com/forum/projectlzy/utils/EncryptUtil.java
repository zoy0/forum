package com.forum.projectlzy.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class EncryptUtil {

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAItK7aCTBn2adgdUEjGAnEGIWhe1yXN472QyV6h0De1YWcdDBJ03r67hNFxpU6Qdb0wvEU9TRwrvjnGIDiQ4ZRABKybaPTfRen0EQMgyTteDrMgdRzZMQd4PP7BESK94FTSWRyIK9MWe0ejyUWdooWSysep9H187KZVivBXPdeNHAgMBAAECgYASazQE2awwC66zxWAqf5Ep8TfIjtc2l7VAEJAazE3TJq2odao0h9qJS2o7FgpjVykSb97gGGj6fzbcuFr2Wyaxxf6b1CXQVIZPLHZoAaqowLdSFlXGuHTLvm2AgjK3JiClYNRKUAAg1yZzENGtTrnQ5IL/O0VoTE0k6r+LZl8vsQJBAOnxNgpQfiBO00Y5vLLc+8pKPN+BEYx0FZw7xUZvlXDPqzDMnqZUkIQ4JwrZvVdA8899u+Ssadgk29FbOBr0xlMCQQCYbRxGGSP4OgddFlVJ1pRuaIvmwENn2Ieo631YYj/BrgHhBxfqUGwwbDMB+RurbZF9pWPCyqXuSjpPQKmJ46i9AkEAlUrQoM5zXuzO2U75Q+/QY2zy4fM9HrPqIpTmNSf6Z06vwemjCSSIQUiQadVAhpE1xmFH6gu5VC1ZdoZPDKzObwJAE54/fFoyAfneBsIgGg/PLOKx1vI5+ryowToF+QE0OYZe4YqbLmJJenYCnqdkQ00n46cMzv5sGt5c4ORurbuqdQJBAKjBmWg0oY3D1q2qIA0NnFFNs6rl5XZ3bEaOjdz5PrKdON4nRN1Aa1BcXHfjfwf6ar5HHVhB2AgUurlrAHtpLfk=";


    /**
     * md5加密
     *
     * @param data 明文
     * @return 加密后的字符串
     */
    public static String md5Encrypt(String data) {
        return DigestUtils.md5Hex(data);
    }


    /**
     * rsa解密
     *
     * @param data 经rsa加密的字符串
     * @return 明文
     */
    public static String rsaDecrypt(String data) {
        try {
            //64位解码加密后的字符串
            byte[] inputByte = Base64.decodeBase64(data.getBytes("UTF-8"));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            String outStr = new String(cipher.doFinal(inputByte));
            return outStr;
        } catch (UnsupportedEncodingException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
