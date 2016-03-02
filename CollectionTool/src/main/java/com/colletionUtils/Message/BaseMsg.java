package com.colletionUtils.Message;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_msg")
public class BaseMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 消息ID
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int msgId;
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

	public int getId() {
		return msgId;
	}

	public void setId(int msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "BaseMsg [msgId=" + msgId + ", date=" + date + ", msgType=" + msgType + ", clientId=" + clientId
				+ ", msgBody=" + msgBody + "]";
	}

}
