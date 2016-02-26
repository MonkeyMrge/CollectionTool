package com.colletionUtils.message;

public class LoggerMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public LoggerMsg(String clientId, LoggerParam loggerParam) {
		setClientId(clientId);
		setMsgType(MsgType.LOGGER);
		setMsgBody(loggerParam);
	}

}
