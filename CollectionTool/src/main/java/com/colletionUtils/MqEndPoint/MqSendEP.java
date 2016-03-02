package com.colletionUtils.MqEndPoint;

import com.colletionUtils.Message.BaseMsg;

public interface MqSendEP {
	public void MsgSend(BaseMsg msg);
}
