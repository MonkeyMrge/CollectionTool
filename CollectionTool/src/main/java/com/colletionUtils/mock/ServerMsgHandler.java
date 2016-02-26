package com.colletionUtils.mock;

import com.colletionUtils.MqEndPoint.MqReceiveEP;
import com.colletionUtils.message.BaseMsg;

public class ServerMsgHandler {
	protected String serverName;

	public ServerMsgHandler(String serverName) {
		super();
		this.serverName = serverName;
	}

	public void start() {

	}

	public void Do(BaseMsg msg) {
		System.out.println(serverName + " receive msg from RabbitMQ: " + msg);
	}

	protected void askMsg(final MqReceiveEP receiveEP) {
		new Thread(new Runnable() {
			public void run() {
				receiveEP.getMsg(ServerMsgHandler.this);
			}
		}).start();
	}

}
