package com.colletionUtils.server;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import io.netty.channel.socket.SocketChannel;

public class NettyChannelMap {
	private static final Logger logger = Logger.getLogger(NettyChannelMap.class);

	/**
	 * ClientId与其对应的SocketChannel
	 */
	private static ConcurrentHashMap<String, SocketChannel> idSocketmap = new ConcurrentHashMap<String, SocketChannel>();
	/**
	 * ClientId与其最近消息的时间
	 */
	private static ConcurrentHashMap<String, Date> statusMap = new ConcurrentHashMap<String, Date>();

	private static String logString;

	public static void add(String clientId, SocketChannel socketChannel) {
		if (!idSocketmap.containsKey(clientId)) {
			Date date = new Date(System.currentTimeMillis());
			idSocketmap.put(clientId, socketChannel);
			statusMap.put(clientId, date);
			logString = "NettyChannelMap:ClientId: " + clientId + " add success!";
			logger.info(logString);
		} else {
			idSocketmap.replace(clientId, socketChannel);
			updateStatus(clientId);
		}
	}

	public static void updateStatus(String clientId) {
		Date date = new Date(System.currentTimeMillis());
		if (statusMap.containsKey(clientId))
			statusMap.replace(clientId, date);
		else {
			logString = "NettyChannelMap:ClientId: " + clientId + " doesn't existed!";
			System.out.println(logString);
			logger.error(logString);
		}
	}

	public static void remove(SocketChannel socketChannel) {
		for (Map.Entry<String, SocketChannel> entry : idSocketmap.entrySet()) {
			if (entry.getValue() == socketChannel) {
				String clientId = entry.getKey();
				removeWithId(clientId);
			}
		}
	}

	private static void removeWithId(String clientId) {
		idSocketmap.remove(clientId);
		statusMap.remove(clientId);
		logString = "NettyChannelMap remove Client:" + clientId + " success!";
		logger.info(logString);
	}

	public static void remove(String clientId) {
		if (idSocketmap.containsKey(clientId)) {
			removeWithId(clientId);
		} else {
			logString = "NettyChannelMap remove -->ClientId: " + clientId + " doesn't existed!";
			System.out.println("" + logString);
			logger.error(logString);
		}
	}

	public static SocketChannel query(String clientId) {
		if (idSocketmap.containsKey(clientId))
			return idSocketmap.get(clientId);
		else {
			logString = "NettyChannelMap get --> ClientId: " + clientId + " doesn't existed!";
			System.out.println(logString);
			logger.error(logString);
		}
		return null;
	}

	public static String query(SocketChannel socketChannel) {
		String clientId = null;
		for (Map.Entry<String, SocketChannel> entry : idSocketmap.entrySet()) {
			if (entry.getValue() == socketChannel)
				clientId = entry.getKey();
		}
		if (clientId == null) {
			logString = "NettyChannelMap query-->SocketChannle: " + socketChannel + " doesn't existed!";
			System.out.println("" + logString);
			logger.info(logString);
		}
		return clientId;
	}

	public static ConcurrentHashMap<String, SocketChannel> getIdSocketMap() {
		return idSocketmap;
	}

	public static ConcurrentHashMap<String, Date> getStatusMap() {
		return statusMap;
	}
}
