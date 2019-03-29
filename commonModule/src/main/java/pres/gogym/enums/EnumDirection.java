package pres.gogym.enums;

/**
 * 消息方向
 * 
 * @author kokJuis
 * @version 1.0
 * @date 2016-9-26
 */
public enum EnumDirection {

	SEND(1), RECEVIE(2);

	private int value;

	// 构造器默认只能是private, 从而保证构造函数只能在内部使用
	private EnumDirection(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
