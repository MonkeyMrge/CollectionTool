package com.colletionUtils.Message.Reply;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

@Entity
@Table(name = "t_msg")
@DiscriminatorValue("ReplyMsg")
public class ReplyMsg extends BaseMsg {
	private static final long serialVersionUID = 1L;

	public ReplyMsg(String clientId, ReplyParam replyParam) {
		setClientId(clientId);
		setMsgType(MsgType.REPLY);
		setMsgBody(replyParam);
	}
}
