package com.colletionUtils.NettyServer;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.colletionUtils.Common.Configs;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author gemengran 使用单例模式
 */
public class NettyMqServer {
	private static final Logger logger = Logger.getLogger(NettyMqServer.class);

	private volatile EventLoopGroup boss;
	private volatile EventLoopGroup worker;
	private volatile Channel serverChannel;
	private static NettyMqServer nettyMqServer;
	/**
	 * Bootstrap用来连接远程主机，有1个EventLoopGroup
	 * ServerBootstrap用来绑定本地端口，有2个EventLoopGroup
	 */
	private volatile ServerBootstrap bootstrap = new ServerBootstrap();
	private volatile Boolean serverClose = false;
	private final int localPort;

	private NettyMqServer() {
		boss = new NioEventLoopGroup();
		worker = new NioEventLoopGroup();
		localPort = Configs.Netty_Server_Port;
	}

	/**
	 * 单例模式 获取唯一server对象
	 */
	public static NettyMqServer getInstance() {
		if (nettyMqServer == null)
			getInstanceSync();
		return nettyMqServer;
	}

	private synchronized static void getInstanceSync() {
		if (nettyMqServer == null)
			nettyMqServer = new NettyMqServer();
	}

	private void start() {
		serverClose = false;
		bootstrap.group(boss, worker);
		/**
		 * NIO io.netty.channel.socket.nio 基于java.nio.channels的工具包，使用选择器作为基础的方法。
		 * OIO io.netty.channel.socket.oio 基于java.net的工具包，使用阻塞流。
		 * Local:io.netty.channel.local 用来在虚拟机之间本地通信。
		 * Embedded:io.netty.channel.embedded 嵌入传输，它允许在没有真正网络的运输中使用
		 * ChannelHandler，可 以非常有用的来测试ChannelHandler的实现。
		 */
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel channel) throws Exception {
				/**
				 * 设置写时超时 读时超时
				 */
				channel.pipeline().addLast(new IdleStateHandler(Configs.Netty_Server_ReaderIdleTimeSeconds,
						Configs.Netty_Server_WriterIdleTimeSeconds, Configs.Netty_Server_AllIdleTimeSeconds));
				/**
				 * 解码器
				 */
				channel.pipeline()
						.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
				/**
				 * 编码器
				 */
				channel.pipeline().addLast(new ObjectEncoder());
				channel.pipeline().addLast(new NettyMqServerHandler());
			}
		});
		bootstrap.option(ChannelOption.SO_BACKLOG, Configs.Netty_Server_SO_Backlog);
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, Configs.Netty_Server_SO_KEEPALIVE);
		bootstrap.childOption(ChannelOption.TCP_NODELAY, Configs.Netty_Server_TCP_NODELAY);
		bootstrap.childOption(ChannelOption.SO_REUSEADDR, Configs.Netty_Server_SO_REUSEADDR);

		bind();

	}

	private void bind() {
		if (serverClose)
			return;

		ChannelFuture future = bootstrap.bind().addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					logger.info("Netty Server starts success! Port = " + localPort);
					System.out.println("Netty Server starts success! Port = " + localPort);
				} else {
					logger.warn("Netty Server starts error ! Port = " + localPort);
					future.channel().eventLoop().schedule(() -> bind(), Configs.Netty_Server_ReConnected_Time,
							TimeUnit.MILLISECONDS);
				}
			}
		});
		serverChannel = future.channel();

	}

	private void close() {
		serverClose = true;
		if (serverChannel != null) {
			serverChannel.close();
		}
		if (worker != null) {
			worker.shutdownGracefully();
		}
		if (boss != null) {
			boss.shutdownGracefully();
		}
		logger.info("Netty Server shut down!");
	}

	public void startServer() {
		try {
			getInstance().start();
			while (true) {
				if (NettyChannelMap.size() == 0)
					TimeUnit.SECONDS.sleep(2);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn(e.getMessage());
		}

	}

	public void closeServer() {
		getInstance().close();
	}
}
