package com.colletionUtils.Message.Ask;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

/**
 * 请求类型消息，默认不做持久化
 */
public class AskMsg extends BaseMsg {
	private static final long serialVersionUID = 1L;

	public AskMsg(String fromUri, Object msgBody) {
		super(fromUri, msgBody);
		setMsgType(MsgType.ASK);
	}

	public AskMsg(String fromUri, Object msgBody, Boolean persist) {
		super(fromUri, msgBody, persist);
		setMsgType(MsgType.ASK);
	}

}
