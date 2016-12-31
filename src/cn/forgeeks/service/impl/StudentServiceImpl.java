package cn.forgeeks.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.StudentDao;
import cn.forgeeks.domain.Student;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.StudentService;

@Repository
public class StudentServiceImpl implements StudentService{
	
	@Resource 
	StudentDao studentDao;
	@Override
	public List<Student> list(Map paraMap) {
		return studentDao.find(paraMap);
	}

	@Override
	public List<Student> findPage(Page page) {
		return studentDao.findPage(page);
	}

	@Override
	public List<Student> find(Map paraMap) {
		return studentDao.find(paraMap);
	}

	@Override
	public Student get(Serializable id) {
		return studentDao.get(id);
	}

	@Override
	public void insert(Student factory) {
		studentDao.insert(factory);
	}

	@Override
	public void update(Student factory) {
		studentDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		studentDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		studentDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {
		return studentDao.findResultSize(page);
	}
	
}
