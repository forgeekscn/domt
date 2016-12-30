package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.BedroomDao;
import cn.forgeeks.domain.Bedroom;
import cn.forgeeks.pagination.Page;

@Repository
public class BedroomDaoImpl extends BaseDaoImpl<Bedroom> implements BedroomDao {

	public BedroomDaoImpl() {
		super.setNs("cn.forgeeks.mapper.BedroomMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
