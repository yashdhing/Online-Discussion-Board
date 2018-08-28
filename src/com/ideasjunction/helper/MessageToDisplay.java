package com.ideasjunction.helper;

public class MessageToDisplay {
	String messageContent;
	String empName;
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "MessageToDisplay [messageContent=" + messageContent
				+ ", empName=" + empName + "]";
	}
}
