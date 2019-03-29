package pres.gogym.enums;

public enum EnumAddFriendStatus {
	// 添加好友请求, 添加好友成功,添加好友失败,拒绝添加好友,同意添加好友,添加好友被拒
	ADD_FRIEND_REQUEST(1), ADD_FRIEND_SUCCESS(2), ADD_FRIEND_FAIL(3), REPULSE_ADD_FRIEND(
			4), AGREE_ADD_FRIEND(5), REFUSED_ADD_FRIEND(6);

	private int value;

	// 构造器默认只能是private, 从而保证构造函数只能在内部使用
	private EnumAddFriendStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
