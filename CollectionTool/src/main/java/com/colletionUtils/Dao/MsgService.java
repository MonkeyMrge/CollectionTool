package com.colletionUtils.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colletionUtils.Message.BaseMsg;

@Service
public class MsgService {

	@Autowired
	private MsgDao msgDao;

	public void addMsg(BaseMsg baseMsg) {
		msgDao.saveMsg(baseMsg);
	}

	public void delMsg(BaseMsg baseMsg) {
		msgDao.deleteMsg(baseMsg);
	}

	public void updateMsg(BaseMsg baseMsg) {
		msgDao.updateMsg(baseMsg);
	}

	public BaseMsg findMsgById(int id) {
		return msgDao.getMsgById(id);
	}
}
