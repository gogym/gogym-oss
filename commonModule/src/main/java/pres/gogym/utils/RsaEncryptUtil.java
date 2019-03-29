package pres.gogym.utils;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
 * RSA非对称加密解密工具类
 * 
 * @ClassName RsaEncryptUtil
 * @author gogym 189155278@qq.com
 * @date 2016-4-6
 * @content
 */
public class RsaEncryptUtil
{

    public static void main(String[] args)
    {

        try
        {
            // String str =
            // "2f/eqKQxKc0buyW49TIpD6KXGu58Qcn24Eq1zbB34rNqst3H1cSqjfE4LU+N19+6s5go44w/xtECYOZ6EkpbqEi4acK5IcVxKEzTwaEsq/kowthSHtvIwqgixfeaQuemweLUNLeRwJvvLfNjs1bTXAswbhiAT9fFQ9CQykjiKcw=";
            // System.out.println("str" + str);

            String s = "eJjPFhWs9YLLbuiyfa4GmZC54zpHvUffb06wIXy0Sj0erScinHwhg571YLRSKP2OyJ6X3aSOEpLGHzlyICVi/lsUiH7GpHlMSzWF1I3hBLrRx8cRYWONpLnKRa9hpOFEuIwW9M1iDdlH1w5J33jmFOK247uO9NW4WBAEvCovpMk=";

            // String dstr = decryptByPrivateKey(str);
            // System.out.println("dstr" + dstr);

            System.out.println(encryptByPublicKey("7c4a8d09ca3762af61e59520943dc26494f8941b"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /** */
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";// RSA/ECB/PKCS1Padding

    /**
     * String to hold name of the encryption padding.
     */
    public static final String PADDING = "RSA/NONE/PKCS1Padding";// RSA/NONE/NoPadding,RSA/NONE/PKCS1Padding,RSA/NONE/OAEPPadding

    /**
     * String to hold name of the security provider.
     */
    public static final String PROVIDER = "BC";

    /** */
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** */
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /** */
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /*
     * 公钥加密
     */
    public static String encryptByPublicKey(String str)
        throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());

        Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

        // 获得公钥
        Key publicKey = getPublicKey();

        // 用公钥加密
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 读数据源
        byte[] data = str.getBytes("UTF-8");

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0)
        {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK)
            {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            }
            else
            {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++ ;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        return Base64Util.encode(encryptedData);
    }

    /**
     * 私钥加密
     * 
     * @param str
     * @return
     * @throws Exception
     * @author kokJuis
     * @date 2016-4-7 下午12:53:15
     * @comment
     */
    public static String encryptByPrivateKey(String str)
        throws Exception
    {

        Security.addProvider(new BouncyCastleProvider());

        Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

        // 获得私钥
        Key privateKey = getPrivateKey();

        // 用私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 读数据源
        byte[] data = str.getBytes("UTF-8");

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0)
        {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK)
            {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            }
            else
            {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++ ;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        return Base64Util.encode(encryptedData);
    }

    /*
     * 公钥解密
     */
    public static String decryptByPublicKey(String str)
        throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());

        Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

        // 获得公钥
        Key publicKey = getPublicKey();

        // 用公钥解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        // 读数据源
        byte[] encryptedData = Base64Util.decode(str);

        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0)
        {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK)
            {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            }
            else
            {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++ ;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();

        return new String(decryptedData, "UTF-8");
    }

    /*
     * 私钥解密
     */
    public static String decryptByPrivateKey(String str)
        throws Exception
    {

        Security.addProvider(new BouncyCastleProvider());

        Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
        // 得到Key
        Key privateKey = getPrivateKey();
        // 用私钥去解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 读数据源
        byte[] encryptedData = Base64Util.decode(str);

        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0)
        {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK)
            {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            }
            else
            {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++ ;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();

        // 二进制数据要变成字符串需解码
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 从文件中读取公钥
     * 
     * @return
     * @throws Exception
     * @author kokJuis
     * @date 2016-4-6 下午4:38:22
     * @comment
     */
    private static Key getPublicKey()
        throws Exception
    {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "properties/rsa_key.properties");
        Properties properties = new Properties();
        properties.load(stream);

        String key = properties.getProperty("rsa_public_key");

        byte[] keyBytes;
        keyBytes = Base64Util.decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 获取私钥
     * 
     * @return
     * @throws Exception
     * @author kokJuis
     * @date 2016-4-7 上午11:46:12
     * @comment
     */
    private static Key getPrivateKey()
        throws Exception
    {

        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "properties/rsa_key.properties");
        Properties properties = new Properties();
        properties.load(stream);

        String key = properties.getProperty("rsa_private_pkcs8");

        byte[] keyBytes;
        keyBytes = Base64Util.decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 得到密钥字符串（经过base64编码）
     * 
     * @return
     */
    public static String getKeyString(Key key)
        throws Exception
    {
        byte[] keyBytes = key.getEncoded();
        String s = Base64Util.encode(keyBytes);
        return s;
    }

    public static byte[] getByte(String str)
    {
        byte[] bt = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try
        {
            if (str != null)
            {
                ObjectOutputStream objos = new ObjectOutputStream(baos);
                objos.writeObject(str);
                bt = baos.toByteArray();
            }
        }
        catch (Exception e)
        {
            bt = (byte[])null;
            e.printStackTrace();

        }
        return bt;
    }

}
