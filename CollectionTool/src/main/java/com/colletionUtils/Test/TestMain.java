package com.colletionUtils.Test;

import com.colletionUtils.server.NettyMqServer;

public class TestMain {
	public static void main(String[] args) {
		NettyMqServer nettyMqServer = NettyMqServer.getInstance();
		nettyMqServer.startServer();
	}
}
