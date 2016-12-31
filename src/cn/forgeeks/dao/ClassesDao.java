package cn.forgeeks.dao;

import cn.forgeeks.domain.Classes;
import cn.forgeeks.pagination.Page;

public interface ClassesDao extends BaseDao<Classes>{
	public String findResultSize(Page page);
}
