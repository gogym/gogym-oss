/*
 * 文件名：MD5Util.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2017年9月19日 跟踪单号： 修改单号： 修改内容：
 */

package pres.gogym.utils;


import java.security.MessageDigest;

/**
 * 
 * 〈MD5加密工具类〉
 * 〈功能详细描述〉
 * @author gogym
 * @version 2017年9月19日
 * @see MD5Util
 * @since
 */
public class MD5Util
{

    public static void main(String[] args)
    {
        String pwd = getMD5("123456");
        System.out.println(pwd);
    }

    // 生成MD5
    public static String getMD5(String message)
    {

        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5"); // 创建一个md5算法对象
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte); // 获得MD5字节数组,16*8=128位
            String md5 = byteToHexString(md5Byte); // 转换为16进制字符串
            return md5;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
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
}
