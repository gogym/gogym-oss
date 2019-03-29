package pres.gogym.enums;

public enum EnumMessageContentType {

	// 定义消息类型
	text(1), image(2), audio(3), video(4);

	private int value;

	// 构造器默认只能是private, 从而保证构造函数只能在内部使用
	private EnumMessageContentType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	// 更加value返回枚举类型
	public static EnumMessageContentType getByValue(int value) {
		for (EnumMessageContentType messageContentType : values()) {
			if (messageContentType.getValue() == value) {
				return messageContentType;
			}
		}
		return null;
	}

}
