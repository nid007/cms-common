package com.github.cms.test;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;

import com.github.cms.bean.Modules;
import com.github.cms.dao.ModulesDao;

public class ModulesTest extends TestCase {
	public void test() {
		
		
		ModulesDao dao = MyBeanFactory.getBean(ModulesDao.class);
		Modules m = dao.get("S");
		System.out.println(m.getTitle());
	}
	public static void main(String[] args) {
		ModulesDao dao = MyBeanFactory.getBean(ModulesDao.class);
		Modules m = dao.get("S");
		System.out.println(m.getTitle());
		
	}

}
