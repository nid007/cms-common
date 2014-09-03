package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.cms.bean.Modules;

public class ModulesDao extends BaseDao<Modules, Integer> {
	static final Logger log = Logger.getLogger(Modules.class);
	
	public List<Modules> getModuleList(boolean showall){
		List<Modules> list = new ArrayList<Modules>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Modules.class);
			if(!showall){
				c.add(Restrictions.eq(Modules.ENABLED, (byte)1));
			}
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
