package com.colletionUtils.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colletionUtils.Dao.MsgService;
import com.colletionUtils.Message.Logger.LoggerMsg;
import com.colletionUtils.Message.Logger.LoggerParam;

public class TestMain {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/SpringContext.xml");
		System.out.println("----->" + context.getBean("sessionFactory"));
		// BaseMsg baseMsg = new BaseMsg();
		// baseMsg.setDate(new Date(System.currentTimeMillis()));
		// baseMsg.setMsgType(MsgType.ASK);
		// baseMsg.setMsgBody(new String("hello"));
		MsgService service = (MsgService) context.getBean("msgService");

		LoggerParam loggerParam = new LoggerParam("warning", "this is log");

//		AskMsg msg = new AskMsg("client001", new AskParam(AskType.LOGIN));

		 LoggerMsg msg = new LoggerMsg("client2", loggerParam);

		// LoginMsg msg = new LoginMsg("client003", new LoginParam("hello",
		// "world"));
		// PingMsg msg = new PingMsg("hello");
		// ReplyMsg msg = new ReplyMsg("client004", new ReplyParam("this is a
		// reply "));
		service.saveMsg(msg);

	}
}
