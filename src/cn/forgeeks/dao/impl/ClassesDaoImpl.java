package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.ClassesDao;
import cn.forgeeks.domain.Classes;
import cn.forgeeks.pagination.Page;

@Repository
public class ClassesDaoImpl extends BaseDaoImpl<Classes> implements ClassesDao {

	public ClassesDaoImpl() {
		super.setNs("cn.forgeeks.mapper.ClassesMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
