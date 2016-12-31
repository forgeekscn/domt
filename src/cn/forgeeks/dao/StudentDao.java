package cn.forgeeks.dao;

import cn.forgeeks.domain.Student;
import cn.forgeeks.pagination.Page;

public interface StudentDao extends BaseDao<Student> {
	public String findResultSize(Page page);
}
