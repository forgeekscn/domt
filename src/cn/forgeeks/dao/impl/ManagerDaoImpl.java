package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.ManagerDao;
import cn.forgeeks.domain.Manager;
import cn.forgeeks.pagination.Page;

@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {

	public ManagerDaoImpl() {
		super.setNs("cn.forgeeks.mapper.ManagerMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

	
}
