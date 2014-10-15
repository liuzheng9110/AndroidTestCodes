package com.example.androidtest.voice;

public class VoiceEntity {
	private String date;
	private String text;
	private String time;
	private boolean isComMeg = true;

	public VoiceEntity() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean getMsgType() {
		return isComMeg;
	}

	public void setMsgType(boolean isComMeg) {
		this.isComMeg = isComMeg;
	};

}
