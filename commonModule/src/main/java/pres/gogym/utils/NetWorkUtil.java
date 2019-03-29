/*
 * 文件名：IpUtil.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2018年5月11日 跟踪单号： 修改单号： 修改内容：
 */

package pres.gogym.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class NetWorkUtil {
	
	
	
	
	public static void main(String[] args) {
		try {
			System.out.println(NetWorkUtil.getLocalMACAddress());
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
	

	/**
	 * 检查IP是否合法
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean ipValid(String ip) {
		String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
		String regex1 = "1\\d{2}";
		String regex2 = "[1-9]\\d";
		String regex3 = "\\d";
		String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|("
				+ regex3 + ")";
		regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex
				+ ")";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ip);
		return m.matches();
	}

	/**
	 * 获取本地ip 适合windows与linux
	 *
	 * @return
	 */
	public static String getLocalIP() {
		String localIP = "127.0.0.1";
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				InetAddress ip = ni.getInetAddresses().nextElement();
				if (!ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {
					localIP = ip.getHostAddress();
					break;
				}
			}
		} catch (Exception e) {
			try {
				localIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		}
		return localIP;
	}

	/**
	 * 获取客户机的ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}

	/**
	 * 把ip转化为整数
	 * 
	 * @param ip
	 * @return
	 */
	public static long ipToLong(String strIp) {
		String[] ip = strIp.split("\\.");
		return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16)
				+ (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
	}

	/**
	 * 将十进制整数形式转换成127.0.0.1形式的ip地址 将整数形式的IP地址转化成字符串的方法如下：
	 * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。
	 * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。
	 * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。
	 * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。
	 * 
	 * @param longIp
	 * @return
	 */
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	
	public static String getLocalMACAddress() throws Exception {
		
		InetAddress ia=InetAddress.getLocalHost();
		
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}

		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}
	
	
	public static String getMACAddress(InetAddress ia) throws Exception {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}

		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}
}
