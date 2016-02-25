package com.colletionUtils.server;

import org.apache.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class NettyMqServer {
	private static final Logger log = Logger.getLogger(NettyMqServer.class);

	private EventLoopGroup boss;
	private EventLoopGroup worker;
	private Channel serverChannel;

	public NettyMqServer() {
		boss = new NioEventLoopGroup();
		worker = new NioEventLoopGroup();
	}

}
