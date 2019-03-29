package pres.gogym.common;

import java.util.HashMap;
import java.util.Map;

import pres.gogym.enums.Code;

/**
 * 返回值对象
 * 
 * @author kokJuis
 */
public class ResponseJson extends HashMap<String, Object> implements
		Map<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ResponseJson ok() {
		ResponseJson rj = new ResponseJson(Code.SUCCESS.getCode(),
				Code.SUCCESS.getMsg());
		return rj;
	}

	public static ResponseJson ok(String msg) {
		ResponseJson rj = new ResponseJson(Code.SUCCESS.getCode(), msg);
		return rj;
	}

	public static ResponseJson ok(Integer code, String msg) {
		ResponseJson rj = new ResponseJson(code, msg);
		return rj;
	}

	public static ResponseJson ok(Object data) {
		ResponseJson rj = new ResponseJson(Code.SUCCESS.getCode(),
				Code.SUCCESS.getMsg(), data);
		return rj;
	}

	public static ResponseJson ok(Integer code, String msg, Object data) {
		return new ResponseJson(code, msg, data);
	}

	public static ResponseJson error() {
		ResponseJson rj = new ResponseJson(Code.FAIL.getCode(),
				Code.FAIL.getMsg());
		return rj;
	}

	public static ResponseJson error(String msg) {
		return new ResponseJson(Code.FAIL.getCode(), msg);
	}

	public static ResponseJson error(Integer code, String msg) {
		return new ResponseJson(code, msg);
	}

	public static ResponseJson error(Integer code, String msg, Object data) {
		return new ResponseJson(code, msg, data);
	}

	public static ResponseJson notParams() {
		ResponseJson rj = new ResponseJson(Code.NOT_PARAMETER.getCode(),
				Code.NOT_PARAMETER.getMsg());
		return rj;
	}

	// --------------------华丽的分割线-----------------------------------
	public ResponseJson() {
	}

	public ResponseJson(Integer code, String msg) {
		this.put("code", code);
		this.put("msg", msg);
	}

	private ResponseJson(Integer code, String msg, Object data) {
		this.put("code", code);
		this.put("msg", msg);
		this.put("data", data);
	}
}
