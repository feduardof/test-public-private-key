package com.example;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CriptografiaUtil {

  public static String gerarChave() throws NoSuchAlgorithmException {
    KeyGenerator generator = KeyGenerator.getInstance("AES");
    generator.init(256); 
    return secretKeyToString(generator.generateKey());
  }

  public static String criptografar(SecretKey secretKey, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
    Cipher aesCipher = Cipher.getInstance("AES");
    aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] bt = aesCipher.doFinal(data.getBytes());
    return toBase4(bt);
  }

  public static String criptografar(String secretKey, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
    return criptografar(stringToSecretKey(secretKey), data);
  }

  public static byte[] descriptografar(SecretKey secretKey, String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    Cipher aesCipher = Cipher.getInstance("AES");
    aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
    return aesCipher.doFinal(fromBase4(data));
  }

  public static byte[] descriptografar(String secretKey, String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
    return descriptografar(stringToSecretKey(secretKey), data);
  }

  public static String toBase4(byte[] msg) {
    return Base64.getEncoder().encodeToString(msg);
  }

  public static byte[] fromBase4(String msg) {
    return Base64.getDecoder().decode(msg);
  }

  public static SecretKey stringToSecretKey(String key) {
    byte[] bkey = fromBase4(key);
    return new SecretKeySpec(bkey , 0, bkey.length, "AES");
  }
  public static String secretKeyToString(SecretKey key) {
    return toBase4(key.getEncoded());
  }
  
}
