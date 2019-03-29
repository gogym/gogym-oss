/*
 * 文件名：GlobalExceptionHandler.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2017年12月14日
 * 跟踪单号： 修改单号： 修改内容：
 */

package pres.gogym.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pres.gogym.common.ResponseJson;
import pres.gogym.utils.LoggerUtils;

// 这边拦截异常，然后封装统一的返回格式
@ControllerAdvice
// @RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = GlobalException.class)
	public ResponseJson jsonErrorHandler(GlobalException e) {

		LoggerUtils.error(this.getClass(), e.getMessage(), e);
		return ResponseJson.error(e.getCode(), e.getMessage(), e.getCause()
				.getMessage());

	}
}
