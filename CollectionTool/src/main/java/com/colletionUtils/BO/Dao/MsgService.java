package com.colletionUtils.BO.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colletionUtils.BO.MsgBO;

@Service
public class MsgService {

	@Autowired
	private MsgDao msgDao;

	public void saveMsg(MsgBO msgBO) {
		msgDao.saveMsg(msgBO);
	}

	public void delMsg(MsgBO msgBO) {
		msgDao.deleteMsg(msgBO);
	}

	public void updateMsg(MsgBO msgBO) {
		msgDao.updateMsg(msgBO);
	}

	public MsgBO findMsgById(String id) {
		return msgDao.getMsgById(id);
	}
}
