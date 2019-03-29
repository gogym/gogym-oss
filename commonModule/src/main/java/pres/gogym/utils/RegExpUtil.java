package pres.gogym.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达工具类
 *
 * @author gogym
 * @date 2016年4月18日 下午4:13:18
 * @comment
 */
public class RegExpUtil {

	// 手机号码或者邮箱
	public final static String regPhoneOrEmail = "0?(13|14|15|18)[0-9]{9}|\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

	// 手机号码
	public final static String regPhone = "^1\\d{10}$";

	// 邮箱
	public final static String regEmail = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

	// 身份证
	public final static String regIdCard = "\\d{17}[\\d|x]|\\d{15}";

	// 中文
	public final static String regChinese = "\\d{17}[\\d|x]|\\d{15}";

	// 网址
	public final static String regUrl = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";

	// 呢称
	public final static String regNickName = "[A-Za-z0-9_\\-\u4e00-\u9fa5]+";

	// 正整数
	public final static String positiveNum = "\\d+";

	// 由数字、26个英文字母或者下划线组成的字符串
	public final static String regLetterAndNum = "\\w+";

	// 匹配方法
	public static boolean regMethod(String reg, String regStr) {

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(regStr);

		return matcher.matches();

	}

}
