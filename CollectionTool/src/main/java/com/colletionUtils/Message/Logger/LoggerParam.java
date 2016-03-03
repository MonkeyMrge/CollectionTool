package com.colletionUtils.Message.Logger;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.colletionUtils.Message.MsgBody;

@Entity
@Table(name = "t_logbody")
public class LoggerParam extends MsgBody implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
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

	public Object getLogBody() {
		return logBody;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public void setLogBody(String logBody) {
		this.logBody = logBody;
	}

	@Id
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = {
			@Parameter(name = "property", value = "loggerMsg") })
	@GeneratedValue(generator = "pkGenerator")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoggerParam [logType=" + logType + ", logBody=" + logBody + "]";
	}

}
