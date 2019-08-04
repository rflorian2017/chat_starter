package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
	// TODO: add message model.
	private String msg;
	private String userStringname;
	private String type;
	private int onlineCount;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Message() {
	}

	public Message(String text) {
		this.msg = text;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserStringname() {
		return userStringname;
	}

	public void setUserStringname(String userStringname) {
		this.userStringname = userStringname;
	}

	public int getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}

	
}
