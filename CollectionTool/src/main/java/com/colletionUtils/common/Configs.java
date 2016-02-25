package com.colletionUtils.common;

public class Configs {

	/////////////////////////////////// 基础配置/////////////////////////////////////////////////////

	public static String ServerId = "Server_0000";
	public static String NETTY_USERNAME = "netty";
	public static String NETTY_PWD = "netty";

	////////////////////////// RabbitMQ基础配置/////////////////////////////////////////////////////
	public static String RabbitMQ_Host = "localhost";
	public static String RabbitMQ_UserName = "guest";
	public static String RabbitMQ_Pwd = "guest";
	public static int RabbitMQ_Port = 15672;
	public static String RabbitMQ_VirtualHost = "/";

	////////////////////////// RabbitMQ个性化配置/////////////////////////////////////////////////////
	public static String RabbitMQ_Exchange_Name = "testExchange";
	public static String RabbitMQ_Exchange_RoutingKey = "testRoutingKey";

	////////////////////////// RabbitMQ接收端配置/////////////////////////////////////////////////////
	/**
	 * 订阅消费模式 topic，广播用fanout，直连用direct
	 */
	public static String RabbitMQ_Exchange_Type = "topic";
	/**
	 * 打开消息持久化
	 */
	public static Boolean RabbitMQ_Exchange_Durable = true;//
	/**
	 * msg自动删除
	 */
	public static Boolean RabbitMQ_Exchange_AutoDelete = true;//
	/**
	 * 消息一条条处理
	 */
	public static int RabbitMQ_Exchange_BasicQos = 1;//
	/**
	 * 打开消息应答机制，false;
	 */
	public static Boolean RabbitMQ_Exchange_AutoAck = false;

	////////////////////////// Netty服务端配置/////////////////////////////////////////////////////

	/**
	 * 读操作空闲时间，单位秒;
	 */
	public static int Netty_Server_ReaderIdleTimeSeconds = 3 * 60;

	/**
	 * 写操作空闲时间，单位秒
	 */
	public static int Netty_Server_WriterIdleTimeSeconds = 0;

	/**
	 * 读写操作空闲时间，单位秒
	 */
	public static int Netty_Server_AllIdleTimeSeconds = 0;

	/**
	 * 队列连接数目,超过就拒绝
	 */
	public static int Netty_Server_SO_Backlog = 128;

	/**
	 * 保持长连接, true;
	 */
	public static Boolean Netty_Server_SO_KEEPALIVE = true;

	/**
	 * 设置封包 使用一次大数据的写操作，而不是多次小数据的写操作,true
	 */
	public static Boolean Netty_Server_TCP_NODELAY = true;
	/**
	 * 让端口释放后立即就可以被再次使用
	 */
	public static Boolean Netty_Server_SO_REUSEADDR = true;
}
