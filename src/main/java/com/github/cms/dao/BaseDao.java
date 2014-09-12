package com.github.cms.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDao<T extends Serializable, PK extends Serializable> extends HibernateDaoSupport  {
	private final Log log = LogFactory.getLog(BaseDao.class);
	

	private Class<T> entityClass;

	private String queryString;

	// 构造方法，根据实例类自动获取实体类类型
	public BaseDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		queryString = "from " + entityClass.getSimpleName() + " model";
	}

	// -------------------- 基本检索、增加、修改、删除操作 --------------------

	/**
	 * 根据主键获取实体。如果没有相应的实体,返回null。
	 * 
	 * @param id
	 *            主键
	 * @return 返回相应实体
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return getHibernateTemplate().get(entityClass, id);
//		T t = null;
//		Session session = null;
//		try {
//			
//			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//			session.beginTransaction();
//			t = (T) session.get(entityClass, id);
//			session.getTransaction().commit();
//			if (t != null)
//				return t;
//		} catch (HibernateException e) {
//			log.error("", e);
//			session.getTransaction().rollback();
//			throw e;
//		}
//		return t;
	}

	/**
	 * 根据主键获取实体。如果没有相应的实体，抛出异常。
	 * 
	 * @param id
	 *            主键
	 * @return 返回相应实体
	 */
	public T load(PK id) {
		return getHibernateTemplate().load(entityClass, id);
//		Session session = null;
//		try {
//			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//			session.beginTransaction();
//			T t = (T) session.load(entityClass, id);
//			session.getTransaction().commit();
//			if (t != null)
//				return t;
//		} catch (HibernateException e) {
//			log.error("", e);
//			session.getTransaction().rollback();
//			throw e;
//		}
//		return null;
	}

	public T loadWithNoTransaction(PK id) {
		return (T)  getHibernateTemplate().getSessionFactory().getCurrentSession().load(entityClass, id);
	}

	/**
	 * 获取全部实体
	 * 
	 * @return 返回相应实体
	 */
	public List<T> loadAll() {
		Session session = null;
		try {
			session =  getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String hql = "from " + entityClass.getSimpleName();
			Query query = session.createQuery(hql);
			List<T> result = query.list();
			session.getTransaction().commit();
			if (result != null && result.size() > 0)
				return result;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
		return null;
	}

	/**
	 * 更新实体
	 * 
	 * @param T
	 *            实体
	 */
	public void update(T entity) {
		
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
	}


	public void evictWithNoTransaction(T entity) {
		 getHibernateTemplate().getSessionFactory().getCurrentSession().evict(entity);
	}

	/**
	 * 存储实体到数据库
	 * 
	 * @param T
	 *            实体
	 */
	public void saveWithNoTransaction(T entity) {
		 getHibernateTemplate().getSessionFactory().getCurrentSession().save(entity);
	}

	/**
	 * 增加或更新实体
	 * 
	 * @param T
	 *            实体
	 */
	public boolean saveOrUpdate(T entity) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
	}

	public PK save(T entity) {
		PK ret = null;
		Session session = null;
		try {
			session =  getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			ret = (PK) session.save(entity);
			session.getTransaction().commit();
			return ret;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
	}

	public void saveOrUpdateWithNoTransaction(T entity) {
		Session session =  getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.saveOrUpdate(entity);
	}

	/**
	 * 增加新集合中的全部实体
	 * 
	 * @param Collection
	 *            实体集合
	 */
	public boolean saveOrUpdateAll(Collection<T> entities) {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (Iterator<T> iter = entities.iterator(); iter.hasNext();) {
				T t = iter.next();
				session.saveOrUpdate(t);
			}
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
	}

	/**
	 * 删除指定的实体
	 * 
	 * @param entity
	 *            实体
	 */
	public boolean delete(T entity) {
		Session session = null;
		try {
			session =  getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
	}

	public void deleteWithNoTransaction(T entity) {
		 getHibernateTemplate().getSessionFactory().getCurrentSession().delete(entity);
	}

	/**
	 * 根据主键删除指定实体
	 * 
	 * @param id
	 *            主键
	 */
	public void deleteByKey(PK id) {
		delete(load(id));
	}

	/**
	 * 删除集合中全部实体
	 * 
	 * @param entities
	 *            实体集合
	 */
	public void deleteAll(Collection<T> entities) {

	}

	// 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
	public void flush() {
		 getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}
}

