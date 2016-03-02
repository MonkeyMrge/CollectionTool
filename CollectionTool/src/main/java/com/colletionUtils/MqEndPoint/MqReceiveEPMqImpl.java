package com.colletionUtils.MqEndPoint;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.colletionUtils.Common.Configs;
import com.colletionUtils.Common.ObjectByteSwap;
import com.colletionUtils.Common.RabbitMQExchangeType;
import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.mock.ServerMsgHandler;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class MqReceiveEPMqImpl extends MqEndPoint implements MqReceiveEP {
	private static final Logger logger = Logger.getLogger(MqReceiveEPMqImpl.class);

	private Connection connection;
	private Channel channel;
	private QueueingConsumer.Delivery delivery;
	private BaseMsg msgReceive;

	public MqReceiveEPMqImpl(RabbitMQExchangeType exchangeType, String exchangeName, String routingKey) {
		super(exchangeType, exchangeName, routingKey);
	}

	private void msgReceive(ServerMsgHandler serverMsgHandler) {
		try {
			connection = connectionFactory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(exchangeName, exchangeType.name());

			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, exchangeName, routingKey);
			// 设置最大服务转发消息数量,Fair dispatch 这可能会导致queue满，需要设置多个client消化消息
			channel.basicQos(Configs.RabbitMQ_Exchange_BasicQos);
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, Configs.RabbitMQ_Exchange_AutoAck, consumer);

			while (true) {
				delivery = consumer.nextDelivery();
				msgReceive = (BaseMsg) ObjectByteSwap.ByteToObject(delivery.getBody());
				logger.info("Receive_EP with RoutingKey" + routingKey + " receive message");
				
				serverMsgHandler.Do(msgReceive);
			}
		} catch (Exception e) {
			logger.error("Create receive Thread ERROR, Exception: " + e.getMessage());
		}
	}

	public void getMsg(ServerMsgHandler serverMsgHandler) {
		msgReceive(serverMsgHandler);
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
