package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.github.cms.bean.Modules;
import com.github.cms.bean.Users;
import com.github.cms.service.bean.InputBean;
import com.github.cms.service.bean.PagerResult;


public class UserDao extends BaseDao<Users, String> {
	static final Logger log = Logger.getLogger(Modules.class);
	
	public PagerResult<Users>  getPagerResult(InputBean input){
		List<Users> list = new ArrayList<Users>();
		Session session = null;
		long total =0;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Users.class);
			
			total = (Long) c.setProjection(   
		                Projections.rowCount()).uniqueResult();   
			c.setProjection(null);

			c.setFirstResult((input.getNowpage() - 1) * input.getPagesize());
			c.setMaxResults(input.getPagesize());

			list = c.list();
			session.getTransaction().commit();			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			log.error("", e);
			throw e;
		}
		return new PagerResult<Users>(input, total, list);
		
	}

}
