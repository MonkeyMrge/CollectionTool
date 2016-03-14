package com.colletionUtils.Message;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

public class BaseMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 消息创建时间
	 */
	private Date date;
	/**
	 * 消息类型
	 */
	private MsgType msgType;
	/**
	 * 消息源ID
	 */
	private String fromUri;

	/**
	 * 消息是否需要持久化标识
	 */
	@Transient
	private Boolean persist;

	private Object msgBody;

	public Boolean getPersist() {
		return persist;
	}

	public void setPersist(Boolean persist) {
		this.persist = persist;
	}

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

	public String getFromUri() {
		return fromUri;
	}

	public void setFromUri(String fromUri) {
		this.fromUri = fromUri;
	}

	public Object getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Object msgBody) {
		this.msgBody = msgBody;
	}

	public BaseMsg(String fromUri, Object msgBody, Boolean persist) {
		super();
		this.date = new Date(System.currentTimeMillis());
		this.fromUri = fromUri;
		this.msgBody = msgBody;
		this.persist = persist;
	}

	public BaseMsg(String fromUri, Object msgBody) {
		super();
		this.date = new Date(System.currentTimeMillis());
		this.fromUri = fromUri;
		this.msgBody = msgBody;
		this.persist = false;
	}

}
