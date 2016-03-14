package com.colletionUtils.Message.Reply;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

public class ReplyParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String msgBody;

	public ReplyParam(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "ReplyParam [msgBody=" + msgBody + "]";
	}

}
