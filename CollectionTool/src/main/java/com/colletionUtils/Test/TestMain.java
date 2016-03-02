package com.colletionUtils.Test;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colletionUtils.Dao.MsgDao;
import com.colletionUtils.Dao.MsgDaoImpl;
import com.colletionUtils.Dao.MsgService;
import com.colletionUtils.Message.BaseMsg;
import com.colletionUtils.Message.MsgType;

public class TestMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/SpringContext.xml");
		System.out.println("----->" + context.getBean("sessionFactory"));
		BaseMsg baseMsg = new BaseMsg();
		baseMsg.setDate(new Date(System.currentTimeMillis()));
		baseMsg.setMsgType(MsgType.ASK);
		baseMsg.setMsgBody(new String("hello"));
		MsgService service = (MsgService) context.getBean("msgService");
		service.addMsg(baseMsg);

	}
}
