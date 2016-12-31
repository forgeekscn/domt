package cn.forgeeks.dao;

import cn.forgeeks.domain.College;
import cn.forgeeks.pagination.Page;

public interface CollegeDao extends BaseDao<College>{
	public String findResultSize(Page page);
}
