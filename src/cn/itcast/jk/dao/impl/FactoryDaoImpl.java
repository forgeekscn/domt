package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月9日
 */
@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao{
	public FactoryDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.FactoryMapper");
	}

	public void updateState(Map map) {
		super.getSqlSession().update(super.getNs()+".updateState", map);
	}
}
