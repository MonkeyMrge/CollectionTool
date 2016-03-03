package com.colletionUtils.RabbitMQ.EndPoint;

import com.colletionUtils.ServerMock.ServerMsgHandler;

public interface MqReceiveEP {
	public void getMsg(ServerMsgHandler serverMsgHandlerMock);
}
