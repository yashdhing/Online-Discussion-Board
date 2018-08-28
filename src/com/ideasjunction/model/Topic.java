package com.ideasjunction.model;

public class Topic {
	    private int topicId;
	    private String topicTitle;
		public int getTopicId() {
			return topicId;
		}
		public void setTopicId(int topicId) {
			this.topicId = topicId;
		}
		public String getTopicTitle() {
			return topicTitle;
		}
		public void setTopicTitle(String topicTitle) {
			this.topicTitle = topicTitle;
		}
		@Override
		public String toString() {
			return "Topic [topicId=" + topicId + ", topicTitle=" + topicTitle
					+ "]";
		}	   
}
