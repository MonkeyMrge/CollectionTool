package com.colletionUtils.message;

public class AskMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public AskMsg(String clientId, AskParam askParam) {
		setClientId(clientId);
		setMsgType(MsgType.ASK);
		setMsgBody(askParam);
	}

}
