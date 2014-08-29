package com.github.cms.test;

import java.io.File;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class MyBeanFactory {
	static final Logger logger = Logger.getLogger(MyBeanFactory.class);
	public static final String xmlPath="testContext.xml";
	static ClassPathXmlApplicationContext context; 
	static{
		init();
	}
	public static void init() {
		
		context = new ClassPathXmlApplicationContext(xmlPath);
		for(String str:context.getBeanDefinitionNames()){
		   logger.info("init spring bean: " + str);
		}
	}
	public static Object getBean(String name){
		return context.getBean(name);
	}
	
	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		
		return context.getBean(requiredType);
	}
	
	public static void main(String[] args) throws SQLException {	 	
		ComboPooledDataSource ds = (ComboPooledDataSource)getBean("dataSourceDefault");
		System.out.println(ds.getConnection());
				
	}
}
