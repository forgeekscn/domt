package cn.forgeeks.dao;

import cn.forgeeks.domain.Visitor;
import cn.forgeeks.pagination.Page;

public interface VisitorDao extends BaseDao<Visitor> {
	public String findResultSize(Page page);
}
