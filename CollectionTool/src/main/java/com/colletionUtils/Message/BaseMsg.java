package com.colletionUtils.Message;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "t_msg")
@DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING, length = 30)
@DiscriminatorValue("BaseMsg")
public class BaseMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 消息ID
	 */
	@Id
	// @GenericGenerator(name = "uuidGen", strategy = "uuid")
	// @GeneratedValue(generator = "uuidGen")
	@GeneratedValue
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
	// /**
	// * 消息体
	// */
	// private MsgBody msgBody;

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

	// @OneToOne(cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn // 这个注解只能写在主(生成ID)的一端
	// public MsgBody getMsgBody() {
	// return msgBody;
	// }
	//
	// public void setMsgBody(MsgBody msgBody) {
	// this.msgBody = msgBody;
	// }

	public int getId() {
		return msgId;
	}

	public void setId(int msgId) {
		this.msgId = msgId;
	}

	// @Override
	// public String toString() {
	// return "BaseMsg [msgId=" + msgId + ", date=" + date + ", msgType=" +
	// msgType + ", clientId=" + clientId
	// + ", msgBody=" + msgBody + "]";
	// }

}
