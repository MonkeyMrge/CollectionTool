package com.colletionUtils.MqEndPoint;

import com.colletionUtils.mock.ServerMsgHandler;

public interface MqReceiveEP {
	public void getMsg(ServerMsgHandler serverMsgHandlerMock);
}
