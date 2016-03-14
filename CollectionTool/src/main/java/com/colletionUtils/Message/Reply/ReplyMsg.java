package com.colletionUtils.Message.Reply;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

/**
 * 答复类消息，默认不做持久化
 */
public class ReplyMsg extends BaseMsg {
	private static final long serialVersionUID = 1L;

	public ReplyMsg(String fromUri, ReplyParam msgBody) {
		super(fromUri, msgBody);
		setMsgType(MsgType.REPLY);
	}

	public ReplyMsg(String fromUri, ReplyParam msgBody, Boolean persist) {
		super(fromUri, msgBody, persist);
		setMsgType(MsgType.REPLY);
	}
}
