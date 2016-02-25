package com.colletionUtils.message;

public class PingMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public PingMsg(String clientId) {
		setClientId(clientId);
		setMsgType(MsgType.PING);
		setMsgBody(new PingParam(clientId));
	}

}
