package com.colletionUtils.Message.Ping;

import java.io.Serializable;

public class PingParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String msgBody;

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public PingParam(String fromUri) {
		super();
		msgBody = "This is a Ping Msg from " + fromUri + "!";
	}

	@Override
	public String toString() {
		return "PingParam [msgBody=" + msgBody + "]";
	}

}
