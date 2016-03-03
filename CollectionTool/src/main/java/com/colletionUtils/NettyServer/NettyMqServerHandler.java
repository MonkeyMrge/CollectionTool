package com.colletionUtils.NettyServer;

import org.apache.log4j.Logger;

import com.colletionUtils.Common.Configs;
import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;
import com.colletionUtils.Message.Ask.AskMsg;
import com.colletionUtils.Message.Ask.AskParam;
import com.colletionUtils.Message.Ask.AskType;
import com.colletionUtils.Message.Login.LoginMsg;
import com.colletionUtils.Message.Login.LoginParam;
import com.colletionUtils.MqEndPoint.MqSendEP;
import com.colletionUtils.MqEndPoint.MqSendEPMqImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class NettyMqServerHandler extends SimpleChannelInboundHandler<BaseMsg> {

	private static final Logger logger = Logger.getLogger(NettyMqServerHandler.class);
	private MqSendEP sendEP;
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
				LoginMsg loginMsg = (LoginMsg) msg;
				LoginParam loginParam = loginMsg.getLoginParam();
				if (loginParam.getUserName().equals(Configs.NETTY_USERNAME)
						&& loginParam.getPassword().equals(Configs.NETTY_PWD)) {
					// Netty Client登录成功，保存其SocketChannel和最新消息时间
					NettyChannelMap.add(clientId, (SocketChannel) ctx.channel());
					logString = ctx.channel() + "--->client: " + clientId + " login Netty Server successed!";
				} else {
					logString = ctx.channel() + "--->client: " + clientId + " login Netty Server fail!-->" + loginParam;
				}
				logger.info(logString);
				System.out.println(logString);
			} else {
				if (NettyChannelMap.containsKey(clientId)) {
					// 更新对应的消息最新时间
					NettyChannelMap.updateStatus(clientId);
					System.out.println(clientId + " -->" + NettyChannelMap.query(clientId));

					switch (msgType) {
					case LOGGER:
						// 日志数据丢到LOG的exchange，使用topic，方便日志分类
						sendEP = new MqSendEPMqImpl(Configs.RabbitMQ_Exchange_Log_Type,
								Configs.RabbitMQ_Exchange_LOG_Name, msgType.name());
						sendEP.MsgSend(msg);
						break;

					default:
						// 默认丢到默认的一个exchange，使用direct
						sendEP = new MqSendEPMqImpl(Configs.RabbitMQ_Exchange_Default_Type,
								Configs.RabbitMQ_Exchange_Default_Name, msgType.name());
						sendEP.MsgSend(msg);
					}
				} else {
					logString = clientId + " does not existed!";
					logger.warn(logString);
					System.out.println(logString);
					AskMsg askMsg = new AskMsg(Configs.ServerId, new AskParam(AskType.LOGIN));
					ctx.writeAndFlush(askMsg);
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
