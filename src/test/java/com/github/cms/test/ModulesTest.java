package com.github.cms.test;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.github.cms.bean.Modules;
import com.github.cms.dao.ModulesDao;

public class ModulesTest extends TestCase {
	public void test() {
		
		
		ModulesDao dao = MyBeanFactory.getBean(ModulesDao.class);
		Modules m = dao.get(1);
		System.out.println(m.getTitle());
	}
	public static void main(String[] args) {
		StandardPasswordEncoder sp = new StandardPasswordEncoder();
		String str =	sp.encode("123");
		System.out.println(str);
		
	}

}
