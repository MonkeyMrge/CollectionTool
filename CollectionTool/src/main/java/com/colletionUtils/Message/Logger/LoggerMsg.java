package com.colletionUtils.Message.Logger;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

/**
 * 日志类消息，默认做持久化
 */
@Entity
@Table(name = "t_msg")
@DiscriminatorValue("LoggerMsg")
public class LoggerMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = LoggerParam.class)
	@JoinColumn(name = "body_id", nullable = false, updatable = false) // 指定一个外键，也可以不指定。
	private LoggerParam loggerParam;

	public LoggerParam getLoggerParam() {
		return loggerParam;
	}

	public void setLoggerParam(LoggerParam loggerParam) {
		this.loggerParam = loggerParam;
	}

	public LoggerMsg(String clientId, LoggerParam loggerParam, Boolean persist) {
		setClientId(clientId);
		setMsgType(MsgType.LOGGER);
		setLoggerParam(loggerParam);
		setPersist(persist);
	}

	public LoggerMsg(String clientId, LoggerParam loggerParam) {
		setClientId(clientId);
		setMsgType(MsgType.LOGGER);
		setLoggerParam(loggerParam);
		setPersist(true);
	}

}
