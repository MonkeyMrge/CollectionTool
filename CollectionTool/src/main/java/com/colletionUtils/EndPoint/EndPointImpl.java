package com.colletionUtils.EndPoint;

import com.colletionUtils.common.Configs;
import com.rabbitmq.client.ConnectionFactory;

public class EndPointImpl {
	protected String exchangeName;
	protected ConnectionFactory connectionFactory;
	protected String routingKey;

	public EndPointImpl(String echangeName, String routingKey) {
		super();
		this.exchangeName = echangeName;
		this.routingKey = routingKey;
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(Configs.RabbitMQ_Host);
		connectionFactory.setUsername(Configs.RabbitMQ_UserName);
		connectionFactory.setPassword(Configs.RabbitMQ_Pwd);
		connectionFactory.setPort(Configs.RabbitMQ_Port);
		connectionFactory.setVirtualHost(Configs.RabbitMQ_VirtualHost);
	}
}