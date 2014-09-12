package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.github.cms.bean.Groups;
import com.github.cms.bean.Modules;
import com.github.cms.bean.Users;
import com.github.cms.service.bean.InputBean;
import com.github.cms.service.bean.PagerResult;


public class GroupDao extends BaseDao<Groups, Integer> {
	static final Logger log = Logger.getLogger(GroupDao.class);
	
	public List<Groups>  getGroupList(){
		List<Groups> list = new ArrayList<Groups>();
		Session session = null;
		long total =0;
		try {
			session =  getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Groups.class);
			
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
