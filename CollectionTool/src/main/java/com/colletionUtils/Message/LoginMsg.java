package com.colletionUtils.Message;

public class LoginMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public LoginMsg(String clientId, LoginParam msgBody) {
		setClientId(clientId);
		setMsgType(MsgType.LOGIN);
		setMsgBody(msgBody);
	}

}
