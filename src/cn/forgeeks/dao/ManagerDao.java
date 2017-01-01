package cn.forgeeks.dao;

import cn.forgeeks.domain.Manager;
import cn.forgeeks.pagination.Page;

public interface ManagerDao extends BaseDao<Manager> {
	public String findResultSize(Page page);
}
