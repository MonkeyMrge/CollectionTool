package com.colletionUtils.message;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class BaseMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 消息创建时间
	 */
	private Date date = new Date(System.currentTimeMillis());
	/**
	 * 消息类型
	 */
	private MsgType msgType;
	/**
	 * 消息源ID
	 */
	private String clientId;
	/**
	 * 消息体
	 */
	private Object msgBody;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Object getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Object msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return msgType + "_Msg [date="
				+ DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(date) + ", msgType="
				+ msgType + ", clientId=" + clientId + ", msgBody=" + msgBody + "]";
	}

}
