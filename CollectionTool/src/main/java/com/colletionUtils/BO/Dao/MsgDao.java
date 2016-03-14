package com.colletionUtils.BO.Dao;

import com.colletionUtils.BO.MsgBO;

public interface MsgDao {
	public void saveMsg(MsgBO msgBO);

	public void deleteMsg(MsgBO msgBO);

	public void updateMsg(MsgBO msgBO);

	public MsgBO getMsgById(String id);
}
