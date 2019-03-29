package pres.gogym.common;

public class Constants {

	// 上传头像大小
	public static final int UPLOAD_HEAD_PORTRAIT_SIZE = 2097152;

	// token有效时间
	public static final int TOKEN_VALID_TIME = 48 * 60 * 60 * 1000;// 48小时

	public static final int TOKEN_REDIS_TIME = 2 * 60 * 60;// 24小时（单位：s）

	// 推送URL
	public static final String PUSH_URL = "http://localhost:8089/PushServer/pushMessage/pushMsg";

	public static final String apiKey = "1dfd23456";

	public static final String secretKey = "dfdf654321";

	// 一级缓存消息最大数量，超过放到二级缓存
	public static final int LEVEL_1_CACHE_MESSAGE_COUNT = 1000;

	// 一级缓存消息超时时间,10分钟
	public static final long LEVEL_1_CACHE_MESSAGE_TIMEINTERVAL = 10 * 60 * 1000;

	// 二级缓存消息超时时间,24个小时
	public static final long LEVEL_2_CACHE_MESSAGE_TIMEINTERVAL = 24 * 60 * 60 * 1000;

	// 消息重发有效时间
	public static final long RESEND_MESSAGE_TIME = 90 * 1000;

	// UDP连接保留时间
	public static final long UDP_SOCKET_TIMEINTERVAL = 1000 * 60 * 5;

	// 协议标识
	public final static String identify = "ys";

	// 协议版本
	public final static String version = "1.0";

	// 群聊前缀
	public final static String REDIS_CHAT_GROUP = "redis_chat_group_";

}
