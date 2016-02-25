package com.colletionUtils.mock;

import com.colletionUtils.common.Configs;
import com.colletionUtils.message.AskMsg;
import com.colletionUtils.message.ReplyMsg;
import com.colletionUtils.message.ReplyParam;

public class AskHandlerMock {

	public static ReplyMsg Do(AskMsg msg) {
		ReplyMsg replyMsg = new ReplyMsg(Configs.ServerId,
				new ReplyParam(new String("this is a msg from AskHandlerMock")));
		return replyMsg;
	}

}
