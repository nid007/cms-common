package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.cms.bean.GroupAuthorities;

public class GroupAuthorityDao extends BaseDao<GroupAuthorities, Long> {
	static final Logger log = Logger.getLogger(GroupAuthorityDao.class);
	
	public List<GroupAuthorities> getGroupAuthorities(int groupId){
		List<GroupAuthorities> list = new ArrayList<GroupAuthorities>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(GroupAuthorities.class);
			c.add(Restrictions.eq(GroupAuthorities.GROUPID,groupId));
			list = c.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			log.error("", e);
			throw e;
		}
		return list;
	}
}
