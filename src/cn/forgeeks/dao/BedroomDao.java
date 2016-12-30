package cn.forgeeks.dao;

import cn.forgeeks.domain.Bedroom;
import cn.forgeeks.pagination.Page;

public interface BedroomDao extends BaseDao<Bedroom>{
	public String findResultSize(Page page);
}
