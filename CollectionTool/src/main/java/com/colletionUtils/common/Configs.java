package com.colletionUtils.common;

public class Configs {
	public static String NETTY_USERNAME = "netty";
	public static String NETTY_PWD = "netty";

	public static String RabbitMQ_Host = "localhost";
	public static String RabbitMQ_UserName = "guest";
	public static String RabbitMQ_Pwd = "guest";
	public static int RabbitMQ_Port = 15672;
	public static String RabbitMQ_VirtualHost = "/";

	public static String RabbitMQ_Exchange_Type = "topic";
	public static Boolean RabbitMQ_Exchange_Durable = true;// 打开消息持久化
	public static Boolean RabbitMQ_Exchange_AutoDelete = true;// msg自动删除
	public static int RabbitMQ_Exchange_BasicQos = 1;// 消息一条条处理
	public static Boolean RabbitMQ_Exchange_AutoAck = false;// 打开消息应答机制

}
