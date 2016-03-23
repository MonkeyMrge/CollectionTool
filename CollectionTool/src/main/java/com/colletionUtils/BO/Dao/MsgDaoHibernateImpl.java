package com.colletionUtils.BO.Dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.colletionUtils.BO.MsgBO;

//@Repository("msgDao")
public class MsgDaoHibernateImpl implements MsgDao {
	private HibernateTemplate hibernateTemplate;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void saveMsg(MsgBO msgBO) {
		hibernateTemplate.save(msgBO);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void deleteMsg(MsgBO msgBO) {
		hibernateTemplate.delete(msgBO);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMsg(MsgBO msgBO) {
		hibernateTemplate.update(msgBO);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public MsgBO getMsgById(String id) {
		return hibernateTemplate.get(MsgBO.class, id);
	}

}
