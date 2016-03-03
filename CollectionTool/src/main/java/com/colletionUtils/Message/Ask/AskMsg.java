package com.colletionUtils.Message.Ask;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

/**
 * 请求类型消息，默认不做持久化
 */
@Entity
@Table(name = "t_msg")
@DiscriminatorValue("AskMsg")
public class AskMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = AskParam.class)
	@JoinColumn(name = "ask_id", nullable = true, updatable = false) // 指定一个外键，也可以不指定。
	private AskParam askParam;

	public AskParam getAskParam() {
		return askParam;
	}

	public void setAskParam(AskParam askParam) {
		this.askParam = askParam;
	}

	public AskMsg(String clientId, AskParam askParam, Boolean persist) {
		setClientId(clientId);
		setMsgType(MsgType.ASK);
		setAskParam(askParam);
		setPersist(persist);
	}

	public AskMsg(String clientId, AskParam askParam) {
		setClientId(clientId);
		setMsgType(MsgType.ASK);
		setAskParam(askParam);
		setPersist(false);
	}
}
