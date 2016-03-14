package com.colletionUtils.Message.Logger;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

/**
 * 日志类消息，默认做持久化
 */
public class LoggerMsg extends BaseMsg {
	private static final long serialVersionUID = 1L;

	public LoggerMsg(String fromUri, LoggerParam msgBody) {
		super(fromUri, msgBody);
		setMsgType(MsgType.LOGGER);
		setPersist(true);
	}

	public LoggerMsg(String fromUri, LoggerParam msgBody, Boolean persist) {
		super(fromUri, msgBody, persist);
		setMsgType(MsgType.LOGGER);
	}
}
