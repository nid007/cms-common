package com.github.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.github.cms.bean.Authorities;
import com.github.cms.bean.Users;
import com.github.cms.service.bean.InputBean;
import com.github.cms.service.bean.PagerResult;


public class AuthorityDao extends BaseDao<Authorities, Integer> {
	static final Logger log = Logger.getLogger(AuthorityDao.class);
	
	

}
