package com.example.androidtest.sinatopic;

import java.io.Serializable;

public class TopicInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String topicId;
	private String topicName;

	public TopicInfo(String topicId, String topicName) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

}
