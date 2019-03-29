/*
 * 文件名：FastDFSException.java
 * 版权：Copyright by www.poly.com
 * 描述：文件上传异常
 * 修改人：gogym
 * 修改时间：2019年3月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.plugin.fastdfs.pool;

public class FastDFSException extends Exception {

	// serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	// 错误编码
	private int errorCode = 0;

	public FastDFSException(int errorCode) {
		this.errorCode = errorCode;
	}

	public FastDFSException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public FastDFSException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public FastDFSException(Throwable cause, int errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public FastDFSException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace, int errorCode) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
	}
}
