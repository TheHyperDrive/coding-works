package edu.slcc.asdv.utilities;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class DESUtil
{
    static byte[] desKey = new byte[]{
        21, 1, -110, 82, -32, -85, -128, -65
    };

    public byte[] getDesKey() {
        return desKey;
    }

    public void setDesKey(byte[] desKey) {
        DESUtil.desKey = desKey;
    }
    
    //>Encrypts user password.
    public static String encrypt(String data)
    {
        String encryptedData = null;
        try
          {
            
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(desKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);

            encryptedData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
          }
        catch (Exception e)
          {
            throw new RuntimeException(e);
          }
        return encryptedData;
    }


    //>Decrypts user password.
    public static String decrypt(String cryptData)
    {
        String decryptedData = null;
        try
          {

            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(desKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            decryptedData = new String(cipher.doFinal(Base64.getDecoder().decode(cryptData)));
          }
        catch (Exception e)
          {

            throw new RuntimeException(e);
          }
        return decryptedData;
    }

    //>Generates random key.
    public static byte[] desKeyRandom(String userName, String password)
    {
    byte [] key = new byte[8];
    
    int hcUserName = userName.hashCode();
    int hcPassword = password.hashCode();
    
    key[0] =  (byte) (hcUserName & 0x000000FF); 
    key[1] =  (byte) ( (hcUserName & 0x0000FF00) >> 8); 
    key[2] =  (byte) ( (hcUserName & 0x00FF0000) >> 16); 
    key[3] =  (byte) ( (hcUserName & 0xFF000000) >> 24); 
    key[4] =  (byte) (hcPassword & 0x000000FF); 
    key[5] =  (byte) ( (hcPassword & 0x0000FF00) >> 8); 
    key[6] =  (byte) ( (hcPassword & 0x00FF0000) >> 16); 
    key[7] =  (byte) ( (hcPassword & 0xFF000000) >> 24);  

    return key;
    }
}
