package com.colletionUtils.BO.Dao;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.util.DBObjectUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.colletionUtils.BO.MsgBO;

//@Repository(value = "msgDao")
public class MsgDaoMongoImpl implements MsgDao {

	private MongoTemplate mongoTemplate;

	@Resource(name = "mongoTemplate")
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void saveMsg(MsgBO msgBO) {
		mongoTemplate.save(msgBO);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void deleteMsg(MsgBO msgBO) {
		mongoTemplate.remove(msgBO);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMsg(MsgBO msgBO) {
		new DBObjectUtils();
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(msgBO.getId())),
				Update.fromDBObject(DBObjectUtils.dbList(new Object[] { msgBO }), new String[] { "id" }), MsgBO.class);
	}

	@Override
	public MsgBO getMsgById(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), MsgBO.class);
	}

}
