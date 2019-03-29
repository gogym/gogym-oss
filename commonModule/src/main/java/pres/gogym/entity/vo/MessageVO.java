package pres.gogym.entity.vo;

public class MessageVO {

	private Integer messageType;
	private String sendId;
	private String sendName;
	private String senderHeadPortrait;
	private String receiverId;
	private String content;
	private Integer contentType;
	private Integer status;

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSenderHeadPortrait() {
		return senderHeadPortrait;
	}

	public void setSenderHeadPortrait(String senderHeadPortrait) {
		this.senderHeadPortrait = senderHeadPortrait;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Integer getStatus() {

		return status;
	}

	public void setStatus(Integer status) {

		this.status = status;
	}

}
