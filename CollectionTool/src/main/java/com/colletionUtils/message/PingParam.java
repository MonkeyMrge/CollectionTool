package com.colletionUtils.message;

import java.io.Serializable;

public class PingParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msgBody;

	public PingParam(String clientId) {
		super();
		msgBody = "This is a Ping Msg from " + clientId + "!";
	}

	@Override
	public String toString() {
		return "PingParam [msgBody=" + msgBody + "]";
	}

}
