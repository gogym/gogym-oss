package pres.gogym.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具
 * 
 * @ClassName SerializeUtil
 * @Description TODO
 * @author gogym 189155278@qq.com
 * @date 2016-4-7
 * @content
 */
public class SerializeUtil {
	/**
	 * 对象序列化为字符串
	 */
	public static String serialize(Object obj) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				byteArrayOutputStream);
		objectOutputStream.writeObject(obj);
		String serStr = byteArrayOutputStream.toString("ISO-8859-1");// 必须是ISO-8859-1
		serStr = java.net.URLEncoder.encode(serStr, "UTF-8");// 编码后字符串不是乱码（不编也不影响功能）

		objectOutputStream.close();
		byteArrayOutputStream.close();
		return serStr;
	}

	/**
	 * 字符串 反序列化为 对象
	 */
	public static Object unSerialize(String serStr) throws Exception {
		String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				redStr.getBytes("ISO-8859-1"));
		ObjectInputStream objectInputStream = new ObjectInputStream(
				byteArrayInputStream);
		Object obj = objectInputStream.readObject();

		objectInputStream.close();
		byteArrayInputStream.close();
		return obj;
	}

	/**
	 * 序列化成byte[]
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-9-27
	 * @param obj
	 * @return
	 */
	public static byte[] serialize2Byte(Object obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {

		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {

		}
		return null;
	}

}
