package com.colletionUtils.Message.Login;

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
@DiscriminatorValue("LoginMsg")
public class LoginMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = LoginMsg.class)
	@JoinColumn(name = "body_id", nullable = false, updatable = false) // 指定一个外键，也可以不指定。
	private LoginParam loginParam;

	public LoginParam getLoginParam() {
		return loginParam;
	}

	public void setLoginParam(LoginParam loginParam) {
		this.loginParam = loginParam;
	}

	public LoginMsg(String clientId, LoginParam loginParam) {
		setClientId(clientId);
		setMsgType(MsgType.LOGIN);
		setLoginParam(loginParam);
	}

}
