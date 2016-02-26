package com.colletionUtils.server;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.colletionUtils.MqEndPoint.MqReceiveEP;
import com.colletionUtils.MqEndPoint.MqReceiveEPMqImpl;
import com.colletionUtils.common.Configs;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
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

	private EventLoopGroup boss;
	private EventLoopGroup worker;
	private Channel serverChannel;
	private static NettyMqServer nettyMqServer;
	private int port;

	private NettyMqServer() {
		boss = new NioEventLoopGroup();
		worker = new NioEventLoopGroup();
		port = Configs.Netty_Server_Port;
	}

	// 单例模式 获取唯一server对象
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
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline().addLast(new IdleStateHandler(Configs.Netty_Server_ReaderIdleTimeSeconds,
							Configs.Netty_Server_WriterIdleTimeSeconds, Configs.Netty_Server_AllIdleTimeSeconds));
					channel.pipeline()
							.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
					channel.pipeline().addLast(new ObjectEncoder());
					channel.pipeline().addLast(new NettyMqServerHandler());
				}
			});
			bootstrap.option(ChannelOption.SO_BACKLOG, Configs.Netty_Server_SO_Backlog);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, Configs.Netty_Server_SO_KEEPALIVE);
			bootstrap.childOption(ChannelOption.TCP_NODELAY, Configs.Netty_Server_TCP_NODELAY);
			bootstrap.childOption(ChannelOption.SO_REUSEADDR, Configs.Netty_Server_SO_REUSEADDR);

			ChannelFuture future = bootstrap.bind(port).sync();
			serverChannel = future.channel();

			if (future.isSuccess()) {
				logger.info("Netty Server starts success!");
				System.out.println("Netty Server starts success!");
			}

		} catch (Exception e) {
			logger.error("Netty Server starts error ! Error message: " + e.getMessage());
			close();
		}
	}

	private void close() {
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
