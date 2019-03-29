package pres.gogym.utils;


import java.security.MessageDigest;


public class SHAUtil
{

    public static void main(String[] args)
    {
        //7c4a8d09ca3762af61e59520943dc26494f8941b
        System.out.println(shaEncode("123456"));
    }

    /***
     * SHA加密 生成40位SHA码
     * 
     * @param 待加密字符串
     * @return 返回40位SHA码
     */
    public static String shaEncode(String inStr)

    {
        MessageDigest sha = null;
        try
        {
            sha = MessageDigest.getInstance("SHA-1");
            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = sha.digest(byteArray);
            String encodeStr = byteToHexString(md5Bytes);

            return encodeStr.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 实现SHA256加密
     * 
     * @param str
     *            加密后的报文
     * @return
     */
    public static String sha256Encode(String str)
    {
        MessageDigest messageDigest;
        String encodeStr = "";
        try
        {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.digest(str.getBytes("UTF-8"));
            encodeStr = byteToHexString(messageDigest.digest());
            return encodeStr;
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
