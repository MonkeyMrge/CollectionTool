package com.colletionUtils.Message.Logger;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

@Entity
@Table(name = "t_msg")
@DiscriminatorValue("LoggerMsg")
public class LoggerMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public LoggerMsg(String clientId, LoggerParam loggerParam) {
		setClientId(clientId);
		setMsgType(MsgType.LOGGER);
		setMsgBody(loggerParam);
	}

}
