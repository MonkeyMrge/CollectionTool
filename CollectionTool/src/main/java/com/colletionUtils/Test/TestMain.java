package com.colletionUtils.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colletionUtils.Dao.MsgService;
import com.colletionUtils.Message.Ask.AskMsg;
import com.colletionUtils.Message.Ask.AskParam;
import com.colletionUtils.Message.Ask.AskType;

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
		// service.addMsg(baseMsg);
		AskParam askParam = new AskParam(AskType.LOGIN);
		AskMsg askMsg = new AskMsg("client001", askParam);
		service.saveMsg(askMsg);
	}
}
