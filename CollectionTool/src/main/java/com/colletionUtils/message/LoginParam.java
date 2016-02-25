package com.colletionUtils.message;

import java.io.Serializable;

public class LoginParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;

	public LoginParam(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginParam [userName=" + userName + ", password=" + password + "]";
	}

}
