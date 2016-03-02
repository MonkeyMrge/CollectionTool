package com.colletionUtils.Message;

public class ReplyMsg extends BaseMsg {
	private static final long serialVersionUID = 1L;

	public ReplyMsg(String clientId, ReplyParam replyParam) {
		setClientId(clientId);
		setMsgType(MsgType.REPLY);
		setMsgBody(replyParam);
	}
}
