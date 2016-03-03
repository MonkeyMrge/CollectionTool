package com.colletionUtils.Message.Login;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

@Entity
@Table(name = "t_msg")
@DiscriminatorValue("LoginMsg")
public class LoginMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public LoginMsg(String clientId, LoginParam msgBody) {
		setClientId(clientId);
		setMsgType(MsgType.LOGIN);
		setMsgBody(msgBody);
	}

}
