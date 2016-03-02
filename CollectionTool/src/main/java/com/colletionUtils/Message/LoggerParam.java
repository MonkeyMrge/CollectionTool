package com.colletionUtils.Message;

import java.io.Serializable;

public class LoggerParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String logType;
	private Object logBody;

	public LoggerParam(String logType, Object logBody) {
		super();
		this.logType = logType;
		this.logBody = logBody;
	}

	public String getLogType() {
		return logType;
	}

	public Object getLogBody() {
		return logBody;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public void setLogBody(Object logBody) {
		this.logBody = logBody;
	}

	@Override
	public String toString() {
		return "LoggerParam [logType=" + logType + ", logBody=" + logBody + "]";
	}

}
