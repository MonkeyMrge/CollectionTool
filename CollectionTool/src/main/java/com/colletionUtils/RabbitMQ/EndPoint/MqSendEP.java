package com.colletionUtils.RabbitMQ.EndPoint;

import com.colletionUtils.Message.BaseMsg;

public interface MqSendEP {
	public void MsgSend(BaseMsg msg);
}
