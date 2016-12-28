package cn.forgeeks.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.forgeeks.domain.Student;
import cn.forgeeks.pagination.Page;

public interface StudentService {

	public  List<Student> list(Map paraMap); 
	
	public List<Student> findPage(Page page);		//分页查询
	public List<Student> find(Map paraMap);			//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public Student get(Serializable id);			//只查询一个，常用于修改
	public void insert(Student factory);			//插入，用实体作为参数
	public void update(Student factory);			//修改，用实体作为参数
	public void deleteById(Serializable id);		//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);			//批量删除；支持整数型和字符串类型ID
	
}
