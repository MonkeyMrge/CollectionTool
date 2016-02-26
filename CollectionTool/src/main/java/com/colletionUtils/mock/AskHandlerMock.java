package com.colletionUtils.mock;

import com.colletionUtils.MqEndPoint.MqReceiveEPMqImpl;
import com.colletionUtils.common.Configs;
import com.colletionUtils.message.MsgType;

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
