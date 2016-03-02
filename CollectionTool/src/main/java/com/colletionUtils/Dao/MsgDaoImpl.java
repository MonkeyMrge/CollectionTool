package com.colletionUtils.Dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.colletionUtils.Message.BaseMsg;

@Repository("msgDao")
public class MsgDaoImpl implements MsgDao {
	private HibernateTemplate hibernateTemplate;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void saveMsg(BaseMsg baseMsg) {
		System.out.println(hibernateTemplate);
		hibernateTemplate.save(baseMsg);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void deleteMsg(BaseMsg baseMsg) {
		hibernateTemplate.delete(baseMsg);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMsg(BaseMsg baseMsg) {
		hibernateTemplate.update(baseMsg);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public BaseMsg getMsgById(int id) {
		return hibernateTemplate.get(BaseMsg.class, id);
	}

}
