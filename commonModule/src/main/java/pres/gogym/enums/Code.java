package pres.gogym.enums;

/**
 * 操作码类
 * 
 * @author kokJuis
 * @version 1.0
 * @date 2017-3-6
 */
public enum Code {

	SUCCESS(10000, "操作成功"), FAIL(10001, "操作失败"), NOT_PARAMETER(10002, "参数丢失"), NO_USER(
			10003, "不存在该用户"), PASSWORD_ERROR(10004, "密码错误"), IS_USER(10005,
			"用户已经存在"), SERVER_EXCEPTION(10006, "服务器异常"), NO_PERMISSION(10007,
			"无访问权限"), AUTHCODE_ERROR(10008, "验证码错误"), TOKEN_ERROR(10009,
			"TOKEN错误"), PARAMETER_ERROR(10010, "参数错误"), NO_CHANGE(10011,
			"没有任何修改"), BUSINESS_LIMIT_CONTROL(10012, "业务限流"), ANALYSIS_ERR(
			10013, "无法解析"), FUSING(10019, "熔断");

	private int code;
	private String msg;

	private Code(int code) {
		this.code = code;
	}

	private Code(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
