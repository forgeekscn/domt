package cn.forgeeks.service.impl;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.StudentDao;
import cn.forgeeks.domain.Student;
import cn.forgeeks.service.StudentService;

@Repository
public class StudentServiceImpl implements StudentService{
	
	@Resource 
	StudentDao studentDao;
	
	public  List<Student> list(){
		return studentDao.find(new HashMap());
	}
	
	
}
