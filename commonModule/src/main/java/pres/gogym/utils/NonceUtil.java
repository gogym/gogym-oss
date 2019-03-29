/*
 * 文件名：NonceUtil.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2017年8月31日 跟踪单号： 修改单号： 修改内容：
 */

package pres.gogym.utils;

public class NonceUtil
{
    /**
     * 为每个用户生成唯一Nonce,且保证每次生成一致
     * 
     * @param key
     * @return
     * @author kokJuis
     * @date 2016-4-28 下午3:26:05
     * @comment
     */
    public static String NonceCreate(String key)
    {

        String str = key + "_nonce_gen";

        String nonce = null;
        // 经过SHA加密
        try
        {
            String shacode = SHAUtil.shaEncode(str);
            // 加密字符串进行base64编码
            nonce = Base64Util.encode(shacode.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return nonce;
    }

}
