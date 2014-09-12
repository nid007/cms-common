package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.github.cms.bean.Authorities;
import com.github.cms.bean.GroupAuthorities;
import com.github.cms.bean.Users;
import com.github.cms.service.bean.InputBean;
import com.github.cms.service.bean.PagerResult;


public class AuthorityDao extends BaseDao<Authorities, Integer> {
	static final Logger log = Logger.getLogger(AuthorityDao.class);
	
	public List<Authorities> getAuthorities(String username){
		List<Authorities> list = new ArrayList<Authorities>();
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Authorities.class);
			c.add(Restrictions.eq(Authorities.USERS,username));
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
