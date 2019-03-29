/*
 * 文件名：ServerException.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2017年12月14日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.exception;

import pres.gogym.enums.Code;

public class ServerException extends GlobalException {

	/**
	 * 意义，目的和功能，以及被用到的地方
	 */

	private static final long serialVersionUID = 1L;

	public ServerException(String message) {
		super(message, Code.SERVER_EXCEPTION.getCode());
	}

	public ServerException(String message, Integer code) {
		super(message, code);
	}
}
