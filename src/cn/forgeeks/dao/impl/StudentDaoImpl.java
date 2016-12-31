package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.StudentDao;
import cn.forgeeks.domain.Student;
import cn.forgeeks.pagination.Page;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	public StudentDaoImpl() {
		super.setNs("cn.forgeeks.mapper.StudentMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

	
}
