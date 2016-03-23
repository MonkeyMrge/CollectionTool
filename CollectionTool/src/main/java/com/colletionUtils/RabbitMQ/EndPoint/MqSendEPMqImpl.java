package com.colletionUtils.RabbitMQ.EndPoint;

import org.apache.log4j.Logger;

import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.RabbitMQ.Common.RabbitMQExchangeType;
import com.colletionUtils.Serialize.ObjectByteSwap;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class MqSendEPMqImpl extends MqEndPoint implements MqSendEP {

	private static final Logger logger = Logger.getLogger(MqSendEP.class);
	private Connection connection;
	private Channel channel;

	public MqSendEPMqImpl(RabbitMQExchangeType exchangeType, String exchangeName, String routingKey) {
		super(exchangeType, exchangeName, routingKey);
	}

	public void MsgSend(BaseMsg msg) {
		try {
			connection = connectionFactory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(exchangeName, exchangeType.name());
			channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,
					ObjectByteSwap.ObjectToByte(msg));
			logger.info("MsgSend--> send message to Exchange: " + exchangeName + " ,with routingKey: " + routingKey
					+ ", exchange type: " + exchangeType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}

	}
}
