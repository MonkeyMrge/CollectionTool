package com.colletionUtils.mock;

import com.colletionUtils.message.BaseMsg;

public class ServerMsgHandlerMock {
	private String serverName;

	public ServerMsgHandlerMock(String serverName) {
		super();
		this.serverName = serverName;
	}

	public Boolean Do(BaseMsg msg) {
		System.out.println(serverName + "receive msg from RabbitMQ: " + msg);
		return true;
	}

}
