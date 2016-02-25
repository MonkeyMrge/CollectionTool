package com.colletionUtils.server;

import org.apache.log4j.Logger;

import com.colletionUtils.EndPoint.SendEP;
import com.colletionUtils.EndPoint.SendEPMqImpl;
import com.colletionUtils.common.Configs;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyMqServer {
	private static final Logger log = Logger.getLogger(NettyMqServer.class);

	private EventLoopGroup boss;
	private EventLoopGroup worker;
	private Channel serverChannel;

	public NettyMqServer(int port) {
		boss = new NioEventLoopGroup();
		worker = new NioEventLoopGroup();
		start(port);
	}

	public void start(int port) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(boss, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel channel) throws Exception {
				channel.pipeline().addLast(new IdleStateHandler(Configs.Netty_Server_ReaderIdleTimeSeconds,
						Configs.Netty_Server_WriterIdleTimeSeconds, Configs.Netty_Server_AllIdleTimeSeconds));
				channel.pipeline().addLast(new NettyMqServerHandler());

				channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, -4, 0));
			}
		});

	}

}
