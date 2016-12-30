package cn.forgeeks.dao;

import cn.forgeeks.domain.Apartment;
import cn.forgeeks.pagination.Page;

public interface ApartmentDao extends BaseDao<Apartment>{
	public String findResultSize(Page page);
}
