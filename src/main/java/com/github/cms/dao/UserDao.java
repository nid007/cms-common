package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.github.cms.bean.Users;
import com.github.cms.service.bean.InputBean;
import com.github.cms.service.bean.PagerResult;


public class UserDao extends BaseDao<Users, String> {
	static final Logger log = Logger.getLogger(UserDao.class);
	
	public PagerResult<Users>  getPagerResult(InputBean input){
		List<Users> list = new ArrayList<Users>();
		Session session = null;
		long total =0;
		try {
			session =  getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Users.class);
			
			if(!StringUtils.isEmpty(input.getUsername())){
				c.add(Restrictions.like(Users.USERNAME, input.getUsername() + "%"));
			}
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
	
	public List<Users> getUsers(){
		List<Users> list = new ArrayList<Users>();
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Users.class);
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
