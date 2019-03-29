package pres.gogym.enums;

public enum EnumMessageType {
	// 连接请求,连接成功,心跳,ack,普通消息，添加好友请求，音视频请求--------
	CONNECT(1), CONNECT_SUCCESS(2), HEART_BEAT(3), ACK(4), NORMAL_MSG(5), ADD_FRIEND(
			6), AV_REQUEST(7), AV_INIT(8), AV_REPULSE(9), AV_ACCEPT(10), AV_OFFER(
			11), AV_ANSWER(12), AV_CANDIDATE(13), CHAT_GROUP_MSG(14);

	private int value;

	// 构造器默认只能是private, 从而保证构造函数只能在内部使用
	private EnumMessageType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	// 更加value返回枚举类型
	public static EnumMessageType getByValue(int value) {
		for (EnumMessageType messageType : values()) {
			if (messageType.getValue() == value) {
				return messageType;
			}
		}
		return null;
	}
}
