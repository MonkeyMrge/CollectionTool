package com.colletionUtils.EndPoint;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.colletionUtils.common.Configs;
import com.colletionUtils.common.ShareUtils;
import com.colletionUtils.message.BaseMsg;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class Send_EP extends EndPoint {

	Logger logger = Logger.getLogger(getClass());

	public Send_EP(String exchangeName) {
		super(exchangeName);
	}

	public void MsgSend(BaseMsg msg, String routingKey) {
		try {
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(exchangeName, Configs.RabbitMQ_Exchange_Type);
			channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,
					ShareUtils.ObjectToByte(msg));
			logger.info(
					"MsgSend--> send message to Exchange: " + exchangeName + " ,with routingKey: " + routingKey + ";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
