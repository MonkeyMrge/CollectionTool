package com.colletionUtils.EndPoint;

import com.colletionUtils.common.Configs;
import com.rabbitmq.client.ConnectionFactory;

public class EndPoint {
	protected String exchangeName;
	protected ConnectionFactory connectionFactory;

	public EndPoint(String echangeName) {
		super();
		this.exchangeName = echangeName;
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(Configs.RabbitMQ_Host);
		connectionFactory.setUsername(Configs.RabbitMQ_UserName);
		connectionFactory.setPassword(Configs.RabbitMQ_Pwd);
		connectionFactory.setPort(Configs.RabbitMQ_Port);
		connectionFactory.setVirtualHost(Configs.RabbitMQ_VirtualHost);
	}
}
