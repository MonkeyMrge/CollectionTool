package com.colletionUtils.mock;

import com.colletionUtils.EndPoint.ReceiveEP;
import com.colletionUtils.message.BaseMsg;

public class ServerMsgHandler {
	private String serverName;

	public ServerMsgHandler(String serverName) {
		super();
		this.serverName = serverName;
	}

	public Boolean Do(BaseMsg msg) {
		System.out.println(serverName + "receive msg from RabbitMQ: " + msg);
		return true;
	}

	public void askMsg(final ReceiveEP receiveEP) {
		new Thread(new Runnable() {
			public void run() {
				receiveEP.getMsg(ServerMsgHandler.this);
			}
		}).start();
	}
}
