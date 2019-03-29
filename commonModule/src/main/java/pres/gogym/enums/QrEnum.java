/*
 * 文件名：QrEnum.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月6日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.enums;

public enum QrEnum {

	USER_QR_PREFIX(10/* 用户二维码前缀 */);

	private int code;

	private QrEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
