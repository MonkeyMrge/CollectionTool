package com.colletionUtils.Message.Ask;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

@Entity
@Table(name = "t_msg")
@DiscriminatorValue("AskMsg")
public class AskMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public AskMsg(String clientId, AskParam askParam) {
		setClientId(clientId);
		setMsgType(MsgType.ASK);
		setMsgBody(askParam);
	}

}
