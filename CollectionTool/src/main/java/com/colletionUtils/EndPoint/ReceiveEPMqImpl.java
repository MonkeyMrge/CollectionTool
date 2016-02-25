package com.colletionUtils.EndPoint;

import org.apache.log4j.Logger;

import com.colletionUtils.common.Configs;
import com.colletionUtils.common.ShareUtils;
import com.colletionUtils.message.BaseMsg;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveEPMqImpl extends EndPointImpl {
	private static final Logger logger = Logger.getLogger(ReceiveEPMqImpl.class);

	private Thread receiveThread;

	public ReceiveEPMqImpl(String name, String routingKey) {
		super(name, routingKey);
	}

	public void msgReceive() {

		receiveThread = new Thread(new Runnable() {
			public void run() {
				try {
					Connection connection = connectionFactory.newConnection();
					Channel channel = connection.createChannel();
					channel.exchangeDeclare(exchangeName, Configs.RabbitMQ_Exchange_Type,
							Configs.RabbitMQ_Exchange_Durable, Configs.RabbitMQ_Exchange_AutoDelete, null);

					String queueName = channel.queueDeclare().getQueue();
					channel.queueBind(queueName, exchangeName, routingKey);
					// 设置最大服务转发消息数量
					channel.basicQos(Configs.RabbitMQ_Exchange_BasicQos);

					QueueingConsumer consumer = new QueueingConsumer(channel);

					channel.basicConsume(queueName, Configs.RabbitMQ_Exchange_AutoAck, consumer);

					while (true) {
						QueueingConsumer.Delivery delivery = consumer.nextDelivery();
						BaseMsg msgReceive = (BaseMsg) ShareUtils.ByteToObject(delivery.getBody());
						logger.info("Receive_EP receive message");

						
						channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
					}
				} catch (Exception e) {
					logger.error("Create receive Thread ERROR, Exception: " + e.getMessage());
				}
			}
		});

		// 设置守护线程
		receiveThread.setDaemon(true);
		receiveThread.start();

	}
}
