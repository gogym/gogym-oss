/*
 * 文件名：ResponseData.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.common;

public class ResponseData<T> {

	private Integer code;
	private String msg;
	private T data;

	public ResponseData() {
	}

	public ResponseData(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResponseData(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

}
