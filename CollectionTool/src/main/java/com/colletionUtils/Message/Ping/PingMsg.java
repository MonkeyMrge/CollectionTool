package com.colletionUtils.Message.Ping;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

/**
 * 心跳类消息，默认不做持久化
 */
public class PingMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public PingMsg(String fromUri, Object msgBody) {
		super(fromUri, msgBody);
		setMsgType(MsgType.PING);
	}

	public PingMsg(String fromUri, Object msgBody, Boolean persist) {
		super(fromUri, msgBody, persist);
		setMsgType(MsgType.PING);
	}

}
