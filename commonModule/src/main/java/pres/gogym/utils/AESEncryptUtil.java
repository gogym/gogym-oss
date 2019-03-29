/*
 * 文件名：AESEncryptUtil.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2017年9月18日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.utils;


import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
 * 〈AES对称加密工具类〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2017年9月19日
 * @see AESEncryptUtil
 * @since
 */
public class AESEncryptUtil
{
    private static final String DEFAULT_CODING = "utf-8";

    private static String key = "209f04ad11cafd80bf2d4fd66665a19c";

    public static void main(String[] args)
    {
        // getKey();

        String en = AESEncode("7c4a8d09ca3762af61e59520943dc26494f8941b");
        System.out.println(en);
        System.out.println(AESDncode(en));
    }

    /**
     * 随机生成原始对称密钥
     */
    private static void getKey()
    {
        try
        {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);// 要生成多少位，只需要修改这里即可128, 192或256
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = byteToHexString(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为" + s.length());
            System.out.println("二进制密钥的长度为" + s.length() * 4);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
    }

    /**
     * 使用指定的字符串产生原始对称密钥
     */
    private static void getKeyByPass(String password)
    {
        // 生成秘钥
        try
        {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            // SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
            kg.init(128, new SecureRandom(password.getBytes()));
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = byteToHexString(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为" + s.length());
            System.out.println("二进制密钥的长度为" + s.length() * 4);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
    }

    /**
     * Description: 转成二进制
     * 
     * @param src
     * @return
     * @see
     */
    private static byte[] asBin(String src)
    {
        if (src.length() < 1) return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++ )
        {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
            encrypted[i] = (byte)(high * 16 + low);
        }
        return encrypted;
    }

    /**
     * byte数组转化为16进制字符串
     * 
     * @param bytes
     * @return
     */
    private static String byteToHexString(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++ )
        {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3)
            {
                sb.append(strHex.substring(6));
            }
            else
            {
                if (strHex.length() < 2)
                {
                    sb.append("0" + strHex);
                }
                else
                {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /*
     * 加密 1.构造密钥生成器 2.根据ecnodeRules规则初始化密钥生成器 3.产生密钥 4.创建和初始化密码器 5.内容加密 6.返回字符串
     */
    public static String AESEncode(String content)
    {
        try
        {

            byte[] input = content.getBytes(DEFAULT_CODING);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(key.getBytes(DEFAULT_CODING));
            SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skc);
            byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
            int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
            ctLength += cipher.doFinal(cipherText, ctLength);
            String str = Base64Util.encode(cipherText);
            return str;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // 如果有错就返加null
        return null;
    }

    /*
     * 解密 解密过程： 1.同加密1-4步 2.将加密后的字符串反纺成byte[]数组 3.将加密内容解密
     */
    public static String AESDncode(String encrypted)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(key.getBytes(DEFAULT_CODING));
            SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");

            Cipher dcipher = Cipher.getInstance("AES");
            dcipher.init(Cipher.DECRYPT_MODE, skc);
            byte[] enByte = Base64Util.decode(encrypted);
            byte[] clearbyte = dcipher.doFinal(enByte);
            return new String(clearbyte);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // 如果有错就返加null
        return null;
    }

    /**
     * 微信AES解密
     * 
     * @param content
     *            密文
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchProviderException
     */
    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte)
        throws InvalidAlgorithmParameterException
    {
        Security.addProvider(new BouncyCastleProvider());
        try
        {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 生成iv
    public static AlgorithmParameters generateIV(byte[] iv)
        throws Exception
    {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }

}
