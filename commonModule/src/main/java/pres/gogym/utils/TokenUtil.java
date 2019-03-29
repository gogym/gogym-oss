package pres.gogym.utils;

import java.util.Random;

/**
 * token生成工具类
 * 
 * @author gogym
 * @version 2017年8月30日
 * @see TokenUtil
 * @since
 */
public class TokenUtil {

	private static Random rnd = new Random();

	/**
	 * 生成唯一Token
	 * 
	 * @param key
	 * @return
	 * @author kokJuis
	 * @date 2016-4-28 下午3:26:05
	 * @comment
	 */
	public static String TokenCreate(String key) {

		String token = null;

		// 生成随机数
		int rNum = rnd.nextInt(10000);
		// 生成随机字符串，加上时间戳，确保唯一
		String str = key + "_" + System.currentTimeMillis() + rNum;

		// 经过SHA加密
		try {
			String shacode = SHAUtil.shaEncode(str);
			// 加密字符串进行base64编码
			token = Base64Util.encode(shacode.getBytes("UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return token;
	}

	/**
	 * 生成唯一Token
	 * 
	 * @return
	 */
	public static String createToken() {

		String token = null;

		// 生成随机数
		int rNum = rnd.nextInt(10000);
		// 生成随机字符串，加上时间戳，确保唯一
		String str = System.currentTimeMillis() + rNum + "";

		// 经过SHA加密
		try {
			String shacode = SHAUtil.shaEncode(str);
			// 加密字符串进行base64编码
			token = Base64Util.encode(shacode.getBytes("UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return token;
	}
}
