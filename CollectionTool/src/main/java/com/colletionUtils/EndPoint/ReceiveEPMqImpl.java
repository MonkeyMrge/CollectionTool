package com.colletionUtils.EndPoint;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.colletionUtils.common.Configs;
import com.colletionUtils.common.ShareUtils;
import com.colletionUtils.message.BaseMsg;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveEPMqImpl extends EndPointImpl implements ReceiveEP {
	private static final Logger logger = Logger.getLogger(ReceiveEPMqImpl.class);

	private Connection connection;
	private Channel channel;
	private QueueingConsumer.Delivery delivery;

	public ReceiveEPMqImpl(RabbitMQExchangeType exchangeType, String exchangeName, String routingKey) {
		super(exchangeType, exchangeName, routingKey);
	}

	private BaseMsg msgReceive() {
		try {
			connection = connectionFactory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(exchangeName, exchangeType.name(), Configs.RabbitMQ_Exchange_Durable,
					Configs.RabbitMQ_Exchange_AutoDelete, null);

			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, exchangeName, routingKey);
			// 设置最大服务转发消息数量,Fair dispatch 这可能会导致queue满，需要设置多个client消化消息
			channel.basicQos(Configs.RabbitMQ_Exchange_BasicQos);

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, Configs.RabbitMQ_Exchange_AutoAck, consumer);

			while (true) {
				delivery = consumer.nextDelivery();
				BaseMsg msgReceive = (BaseMsg) ShareUtils.ByteToObject(delivery.getBody());
				logger.info("Receive_EP with RoutingKey" + routingKey + " receive message");
				return msgReceive;
			}
		} catch (Exception e) {
			logger.error("Create receive Thread ERROR, Exception: " + e.getMessage());
		}
		return null;
	}

	public BaseMsg getMsg() {
		return msgReceive();
	}

	public void ack() {
		try {
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("ServerMsgHandler ack error" + e.getMessage());
		}
	}

}
