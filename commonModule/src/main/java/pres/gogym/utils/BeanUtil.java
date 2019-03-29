/*
 * 文件名：BeanUtil.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.utils;

import java.lang.reflect.Field;

public class BeanUtil {

	/**
	 * java反射机制判断对象所有属性是否全部为空
	 * 
	 * @param obj
	 * @return 返回属性名称
	 */
	public static boolean checkObjFieldIsNull(Object obj) {
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);

				if (f.get(obj) != null && f.get(obj) != "") {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
