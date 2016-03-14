package com.colletionUtils.Message.Logger;

import java.io.Serializable;

public class LoggerParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String logType;
	private String logBody;

	public LoggerParam(String logType, String logBody) {
		super();
		this.logType = logType;
		this.logBody = logBody;
	}

	public String getLogType() {
		return logType;
	}

	public String getLogBody() {
		return logBody;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public void setLogBody(String logBody) {
		this.logBody = logBody;
	}

	@Override
	public String toString() {
		return "LoggerParam [logType=" + logType + ", logBody=" + logBody + "]";
	}

}
