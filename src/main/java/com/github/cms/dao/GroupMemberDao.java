package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.github.cms.bean.GroupMembers;
import com.github.cms.bean.Modules;
import com.github.cms.bean.Users;
import com.github.cms.service.bean.InputBean;
import com.github.cms.service.bean.PagerResult;


public class GroupMemberDao extends BaseDao<GroupMembers, Long> {
	static final Logger log = Logger.getLogger(GroupMemberDao.class);
	
	
	
	public List<GroupMembers> getGroupMembers(int groupId){
		List<GroupMembers> list = new ArrayList<GroupMembers>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(GroupMembers.class);
			c.add(Restrictions.eq(GroupMembers.GROUPID,groupId));
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
