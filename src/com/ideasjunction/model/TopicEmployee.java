package com.ideasjunction.model;

public class TopicEmployee {
	private long topicId;
	private long empId;
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	@Override
	public String toString() {
		return "TopicEmployee [topicId=" + topicId + ", empId=" + empId + "]";
	}
}
