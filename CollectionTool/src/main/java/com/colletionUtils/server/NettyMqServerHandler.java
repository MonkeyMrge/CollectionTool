package com.colletionUtils.server;

import org.apache.log4j.Logger;

import com.colletionUtils.EndPoint.SendEP;
import com.colletionUtils.EndPoint.SendEPMqImpl;
import com.colletionUtils.common.Configs;
import com.colletionUtils.message.AskMsg;
import com.colletionUtils.message.AskParam;
import com.colletionUtils.message.AskType;
import com.colletionUtils.message.BaseMsg;
import com.colletionUtils.message.LoginParam;
import com.colletionUtils.message.MsgType;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class NettyMqServerHandler extends SimpleChannelInboundHandler<BaseMsg> {

	private static final Logger logger = Logger.getLogger(NettyMqServerHandler.class);
	private SendEP sendEP;
	private String logString;

	public NettyMqServerHandler() {
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// 超时处理机制
		if (evt instanceof IdleStateEvent) {
			super.userEventTriggered(ctx, evt);
			if (evt instanceof IdleStateEvent) {
				IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
				switch (idleStateEvent.state()) {
				case READER_IDLE:
					// 读超时异常,Netty Server给Client发消息，请求Client状态
					logger.info("Netty Server: read time out from " + ctx.channel().remoteAddress());
					AskMsg askMsg = new AskMsg(Configs.ServerId, new AskParam(AskType.STATUS_REPORT));
					ctx.writeAndFlush(askMsg);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
		try {
			String clientId = msg.getClientId();

			MsgType msgType = msg.getMsgType();
			logString = "Netty Server receive msg from " + clientId + " , msg is " + msg;
			logger.info(logString);
			System.out.println(logString);

			if (clientId == null) {
				// clientId防null检查,null的话 要求重新登录
				AskMsg askMsg = new AskMsg(Configs.ServerId, new AskParam(AskType.LOGIN));
				ctx.writeAndFlush(askMsg);
			} else if (msgType == null) {
				logString = "MsgType is null!";
				logger.error(logString);
				System.out.println(logString);
			} else if (msgType.equals(MsgType.LOGIN)) {
				// Login类型消息处理
				LoginParam loginParam = (LoginParam) msg.getMsgBody();
				if (loginParam.getUserName().equals(Configs.NETTY_USERNAME)
						&& loginParam.getPassword().equals(Configs.NETTY_PWD)) {
					// Netty Client登录成功，保存其SocketChannel和最新消息时间
					NettyChannelMap.add(clientId, (SocketChannel) ctx.channel());
					logger.info(ctx.channel() + "client: " + clientId + " login Netty Server successed!");
				}
			} else {

				switch (msgType) {
				case PING:
					// 把消息丢到Configs.RabbitMQ_Exchange_Name的Exchange,routingKey为其类型，方便后面分类取
					sendEP = new SendEPMqImpl(Configs.RabbitMQ_Exchange_Default_Type,
							Configs.RabbitMQ_Exchange_LOG_Name, msgType.name());
					// 更新对应的消息最新时间
					NettyChannelMap.updateStatus(clientId);
					sendEP.MsgSend(msg);
					break;
				default:
					// 把消息丢到Configs.RabbitMQ_Exchange_Name的Exchange,routingKey为其类型，方便后面分类取
					sendEP = new SendEPMqImpl(Configs.RabbitMQ_Exchange_Default_Type,
							Configs.RabbitMQ_Exchange_Default_Name, msgType.name());
					// 更新对应的消息最新时间
					NettyChannelMap.updateStatus(clientId);
					sendEP.MsgSend(msg);
				}
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		logger.warn(ctx + " caught Exception: " + cause.getMessage());
	}

}
