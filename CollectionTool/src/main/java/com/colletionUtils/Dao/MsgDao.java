package com.colletionUtils.Dao;

import com.colletionUtils.Message.BaseMsg;

public interface MsgDao {
	public void saveMsg(BaseMsg baseMsg);

	public void deleteMsg(BaseMsg baseMsg);

	public void updateMsg(BaseMsg baseMsg);

	public BaseMsg getMsgById(int id);
}
