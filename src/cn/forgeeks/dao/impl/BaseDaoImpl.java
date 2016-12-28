package cn.forgeeks.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import cn.forgeeks.dao.BaseDao;
import cn.forgeeks.pagination.Page;

public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T>{
	@Autowired
	//mybatis-spring 1.0无需此方法；mybatis-spring1.2必须注入。
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	private String ns;		//命名空间
	public String getNs() {
		return ns;
	}
	public void setNs(String ns) {
		this.ns = ns;
	}
	
	public List<T> findPage(Page page){
		List<T> oList = this.getSqlSession().selectList(ns + ".findPage", page);
		return oList;
	}

	public List<T> find(Map map) {
		List<T> oList = this.getSqlSession().selectList(ns + ".find", map);
		return oList;
	}
	public T get(Serializable id) {
		return this.getSqlSession().selectOne(ns + ".get", id);
	}

	public void insert(T entity) {
		this.getSqlSession().insert(ns + ".insert", entity);
	}

	public void update(T entity) {
		this.getSqlSession().update(ns + ".update", entity);
	}

	public void deleteById(Serializable id) {
		this.getSqlSession().delete(ns + ".deleteById", id);
	}

	public void delete(Serializable[] ids) {
		this.getSqlSession().delete(ns + ".delete", ids);
	}
}
