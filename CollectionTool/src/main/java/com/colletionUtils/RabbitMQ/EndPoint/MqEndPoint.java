package com.colletionUtils.RabbitMQ.EndPoint;

import com.colletionUtils.Common.Configs;
import com.colletionUtils.RabbitMQ.Common.RabbitMQExchangeType;
import com.rabbitmq.client.ConnectionFactory;

public class MqEndPoint {
	protected String exchangeName;
	protected ConnectionFactory connectionFactory;
	protected String routingKey;
	protected RabbitMQExchangeType exchangeType;

	public MqEndPoint(RabbitMQExchangeType exchangeType, String echangeName, String routingKey) {
		super();
		this.exchangeType = exchangeType;
		this.exchangeName = echangeName;
		this.routingKey = routingKey;
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(Configs.RabbitMQ_Host);
		// connectionFactory.setUsername(Configs.RabbitMQ_UserName);
		// connectionFactory.setPassword(Configs.RabbitMQ_Pwd);
		// connectionFactory.setPort(Configs.RabbitMQ_Port);
		// connectionFactory.setVirtualHost(Configs.RabbitMQ_VirtualHost);
	}
}
