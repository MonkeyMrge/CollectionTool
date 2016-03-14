package com.colletionUtils.Message.Login;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

/**
 * 登录类消息，默认不做持久化
 */
public class LoginMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	public LoginMsg(String fromUri, LoginParam msgBody) {
		super(fromUri, msgBody);
		setMsgType(MsgType.LOGIN);
	}

	public LoginMsg(String fromUri, LoginParam msgBody, Boolean persist) {
		super(fromUri, msgBody, persist);
		setMsgType(MsgType.LOGIN);
	}

}
