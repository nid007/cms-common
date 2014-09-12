package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.github.cms.bean.Roles;


public class RoleDao extends BaseDao<Roles, Integer> {
	static final Logger log = Logger.getLogger(RoleDao.class);
	
	public List<Roles>  getRoleList(){
		List<Roles> list = new ArrayList<Roles>();
		Session session = null;
		try {
			session =  getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Roles.class);
			
			list = c.list();
			session.getTransaction().commit();			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			log.error("", e);
			throw e;
		}
		return list;
		
	}
	
	public String[] getRoleArr(){
		List<Roles> list =getRoleList();
		
		List<String> l = new ArrayList<String>();
		for(Roles r:list){
			l.add(r.getTitle());
		}
		return l.toArray(new String[0]);
	}

}
