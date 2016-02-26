package com.colletionUtils.mock;

import com.colletionUtils.EndPoint.ReceiveEPMqImpl;
import com.colletionUtils.common.Configs;
import com.colletionUtils.message.MsgType;

public class AskHandlerMock extends ServerMsgHandler {

	public AskHandlerMock(String serverName) {
		super(serverName);
	}

	public void start() {
		AskHandlerMock askHandlerMock = new AskHandlerMock("AskServer 001");
		askHandlerMock.askMsg(new ReceiveEPMqImpl(Configs.RabbitMQ_Exchange_Default_Type,
				Configs.RabbitMQ_Exchange_Default_Name, MsgType.ASK.name()));
	}

}
