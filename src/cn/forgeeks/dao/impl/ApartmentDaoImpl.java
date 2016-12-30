package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.ApartmentDao;
import cn.forgeeks.domain.Apartment;
import cn.forgeeks.pagination.Page;

@Repository
public class ApartmentDaoImpl extends BaseDaoImpl<Apartment> implements ApartmentDao {

	public ApartmentDaoImpl() {
		super.setNs("cn.forgeeks.mapper.ApartmentMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
