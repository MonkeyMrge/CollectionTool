package com.colletionUtils.BO;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.Ask.AskParam;
import com.colletionUtils.Message.Logger.LoggerParam;
import com.colletionUtils.Message.Login.LoginParam;
import com.colletionUtils.Message.Ping.PingParam;
import com.colletionUtils.Message.Reply.ReplyParam;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "t_msg")
// @DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType =
// DiscriminatorType.STRING, length = 30)
// @DiscriminatorValue("BaseMsg")
public class MsgBO {

	/**
	 * 消息ID
	 */
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "msg_id", insertable = true, updatable = true, nullable = false)
	private String id;

	/**
	 * 消息创建时间
	 */
	private Date date;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 消息源ID
	 */
	private String fromUri;

	/**
	 * 消息体
	 */
	private HashMap<String, String> msgBody = new HashMap<String, String>();

	public MsgBO(BaseMsg baseMsg) {
		setDate(baseMsg.getDate());
		setFromUri(baseMsg.getFromUri());
		setMsgType(baseMsg.getMsgType().name());
		switch (baseMsg.getMsgType()) {
		case LOGGER:
			LoggerParam logBody = (LoggerParam) baseMsg.getMsgBody();
			msgBody.put("LOG_TYPE", logBody.getLogType());
			msgBody.put("LOG_CONTENT", logBody.getLogBody());
			break;
		case LOGIN:
			LoginParam loginBody = (LoginParam) baseMsg.getMsgBody();
			msgBody.put("USER_NAME", loginBody.getUserName());
			msgBody.put("PASSWORD", loginBody.getPassword());
			break;
		case ASK:
			AskParam askBody = (AskParam) baseMsg.getMsgBody();
			msgBody.put("ASK_TYPE", askBody.getAskType().name());
			break;
		case REPLY:
			ReplyParam replyBody = (ReplyParam) baseMsg.getMsgBody();
			msgBody.put("REPLY_BODY", replyBody.getMsgBody());
			break;
		case PING:
			PingParam pingBody = (PingParam) baseMsg.getMsgBody();
			msgBody.put("PING_BODY", pingBody.getMsgBody());
			break;

		default:

			break;
		}
	}

	public MsgBO() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public String getMsgType() {
		return msgType;
	}

	public String getFromUri() {
		return fromUri;
	}

	public HashMap<String, String> getMsgBody() {
		return msgBody;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setFromUri(String fromUri) {
		this.fromUri = fromUri;
	}

	public void setMsgBody(HashMap<String, String> msgBody) {
		this.msgBody = msgBody;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "MsgBO [id=" + id + ", date=" + date + ", msgType=" + msgType + ", fromUri=" + fromUri + ", msgBody="
				+ msgBody + "]";
	}

}
