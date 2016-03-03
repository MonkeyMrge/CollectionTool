package com.colletionUtils.Message.Ping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

@Entity
@Table(name = "t_msg")
@DiscriminatorValue("PingMsg")
public class PingMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public PingMsg(String clientId) {
		setClientId(clientId);
		setMsgType(MsgType.PING);
		setMsgBody(new PingParam(clientId));
	}

}
