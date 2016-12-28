package cn.forgeeks.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.forgeeks.dao.StudentDao;
import cn.forgeeks.domain.Student;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	StudentDao studentDao;

	public List<Student> findPage(Page page) {
		return studentDao.findPage(page);
	}

	public List<Student> find(Map paraMap) {
		return studentDao.find(paraMap);																																		
	}

	public Student get(Serializable id) {
		return studentDao.get(id);
	}

	public void insert(Student student) {
		student.setStudentId(UUID.randomUUID().toString());
		studentDao.insert(student);
	}

	public void update(Student student) {
		studentDao.update(student);
	}

	public void deleteById(Serializable id) {
		studentDao.deleteById(id);
	}

	public void delete(Serializable[] ids) {
		studentDao.delete(ids);
	}

}
