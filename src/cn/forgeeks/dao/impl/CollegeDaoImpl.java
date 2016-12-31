package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.CollegeDao;
import cn.forgeeks.domain.College;
import cn.forgeeks.pagination.Page;

@Repository
public class CollegeDaoImpl extends BaseDaoImpl<College> implements CollegeDao {

	public CollegeDaoImpl() {
		super.setNs("cn.forgeeks.mapper.CollegeMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
