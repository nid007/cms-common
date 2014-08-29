package com.github.cms.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BaseDao<T extends Serializable, PK extends Serializable> {
	private final Log log = LogFactory.getLog(BaseDao.class);
	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

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
		T t = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			t = (T) session.get(entityClass, id);
			session.getTransaction().commit();
			if (t != null)
				return t;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
		return t;
	}

	/**
	 * 根据主键获取实体。如果没有相应的实体，抛出异常。
	 * 
	 * @param id
	 *            主键
	 * @return 返回相应实体
	 */
	public T load(PK id) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			T t = (T) session.load(entityClass, id);
			session.getTransaction().commit();
			if (t != null)
				return t;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
		return null;
	}

	public T loadWithNoTransaction(PK id) {
		return (T) sessionFactory.getCurrentSession().load(entityClass, id);
	}

	/**
	 * 获取全部实体
	 * 
	 * @return 返回相应实体
	 */
	public List<T> loadAll() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
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
	public boolean update(T entity) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			log.error("", e);
			session.getTransaction().rollback();
			throw e;
		}
	}

	public void updateWithNoTransaction(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void evictWithNoTransaction(T entity) {
		sessionFactory.getCurrentSession().evict(entity);
	}

	/**
	 * 存储实体到数据库
	 * 
	 * @param T
	 *            实体
	 */
	public void saveWithNoTransaction(T entity) {
		sessionFactory.getCurrentSession().save(entity);
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
			session = sessionFactory.getCurrentSession();
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
			session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
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
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			for (Iterator<T> iter = entities.iterator(); iter.hasNext();) {
				T t = iter.next();
				sessionFactory.getCurrentSession().saveOrUpdate(t);
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
			session = sessionFactory.getCurrentSession();
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
		sessionFactory.getCurrentSession().delete(entity);
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
		sessionFactory.getCurrentSession().flush();
	}
}

