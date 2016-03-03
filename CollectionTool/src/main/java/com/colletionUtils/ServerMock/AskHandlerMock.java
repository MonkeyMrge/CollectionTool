package com.colletionUtils.ServerMock;

import com.colletionUtils.Common.Configs;
import com.colletionUtils.Message.MsgType;
import com.colletionUtils.RabbitMQ.EndPoint.MqReceiveEPMqImpl;

public class AskHandlerMock extends ServerMsgHandler {

	public AskHandlerMock(String serverName) {
		super(serverName);
	}

	public void start() {
		AskHandlerMock askHandlerMock = new AskHandlerMock(serverName);
		askHandlerMock.askMsg(new MqReceiveEPMqImpl(Configs.RabbitMQ_Exchange_Default_Type,
				Configs.RabbitMQ_Exchange_Default_Name, MsgType.ASK.name()));
	}

}
