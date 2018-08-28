package com.ideasjunction.model;

public class Message {
	private int messageId =1;
	private String messageContent="test";
	private int userId=1;
	private int topicId=2;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageContent="
				+ messageContent + ", userId=" + userId + ", topicId="
				+ topicId + "]";
	}
	
}
