/*
 * 文件名：SmsTemplate.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2018年2月8日 跟踪单号： 修改单号： 修改内容：
 */

package pres.gogym.enums;

public enum EnumSmsTemplate {

	sms_register(1, "_sms_register", "SMS_125900037"/* 注册 */), sms_login(2,
			"_sms_login", "SMS_125900039"/* 登录 */), sms_editphone(3,
			"_sms_editphone", "SMS_125900035"/* 修改手机号 */), sms_resetpassword(4,
			"_sms_resetpassword", "SMS_125900036"/* 修改密码 */), sms_wxregister(5,
			"_sms_wxregister", "SMS_125900035"/* 微信注册 */);

	private int code;

	private String msg;

	private String templateCode;// 短信模板编码

	private EnumSmsTemplate(int code, String msg, String templateCode) {
		this.code = code;
		this.msg = msg;
		this.templateCode = templateCode;
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

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	// 根据value返回枚举类型,主要在switch中使用
	public static EnumSmsTemplate getByCode(int value) {
		for (EnumSmsTemplate code : values()) {
			if (code.getCode() == value) {
				return code;
			}
		}
		return null;
	}

}
