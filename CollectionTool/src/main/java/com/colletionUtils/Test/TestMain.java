package com.colletionUtils.Test;

import com.colletionUtils.mock.AskHandlerMock;
import com.colletionUtils.mock.ServerMsgHandler;
import com.colletionUtils.server.NettyMqServer;

public class TestMain {
	public static void main(String[] args) {

		new Thread(new Runnable() {
			public void run() {
				ServerMsgHandler handler = new AskHandlerMock("Ask001");
				handler.start();
			}
		}).start();

		NettyMqServer nettyMqServer = NettyMqServer.getInstance();
		nettyMqServer.startServer();

	}
}
