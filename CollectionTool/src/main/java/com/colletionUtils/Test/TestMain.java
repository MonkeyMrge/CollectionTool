package com.colletionUtils.Test;

import com.colletionUtils.message.LoginMsg;
import com.colletionUtils.message.LoginParam;
import com.colletionUtils.message.PingMsg;

public class TestMain {
	public static void main(String[] args) {
		LoginMsg loginMsg = new LoginMsg("1101", new LoginParam("hello", "world"));
		System.out.println(loginMsg);
		PingMsg pingMsg = new PingMsg("1102");
		System.out.println(pingMsg);

	}
}
