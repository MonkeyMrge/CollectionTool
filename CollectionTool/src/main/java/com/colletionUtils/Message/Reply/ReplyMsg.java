package com.colletionUtils.Message.Reply;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;
import com.colletionUtils.Message.Ask.AskParam;

@Entity
@Table(name = "t_msg")
@DiscriminatorValue("ReplyMsg")
public class ReplyMsg extends BaseMsg {
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ReplyParam.class)
	@JoinColumn(name = "body_id", nullable = false, updatable = false) // 指定一个外键，也可以不指定。
	private ReplyParam replyParam;

	public ReplyParam getReplyParam() {
		return replyParam;
	}

	public void setReplyParam(ReplyParam replyParam) {
		this.replyParam = replyParam;
	}

	public ReplyMsg(String clientId, ReplyParam replyParam) {
		setClientId(clientId);
		setMsgType(MsgType.REPLY);
		setReplyParam(replyParam);
	}
}
