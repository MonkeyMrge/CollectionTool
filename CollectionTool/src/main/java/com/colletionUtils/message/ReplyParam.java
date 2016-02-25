package com.colletionUtils.message;

import java.io.Serializable;

public class ReplyParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object msgBody;

	public ReplyParam(Object msgBody) {
		this.msgBody = msgBody;
	}

	public Object getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Object msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "ReplyParam [msgBody=" + msgBody + "]";
	}

}
