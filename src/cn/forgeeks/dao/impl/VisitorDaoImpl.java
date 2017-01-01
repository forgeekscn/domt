package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.VisitorDao;
import cn.forgeeks.domain.Visitor;
import cn.forgeeks.pagination.Page;

@Repository
public class VisitorDaoImpl extends BaseDaoImpl<Visitor> implements VisitorDao {

	public VisitorDaoImpl() {
		super.setNs("cn.forgeeks.mapper.VisitorMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

	
}
