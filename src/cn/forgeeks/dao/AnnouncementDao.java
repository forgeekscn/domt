package cn.forgeeks.dao;

import cn.forgeeks.domain.Announcement;
import cn.forgeeks.pagination.Page;

public interface AnnouncementDao extends BaseDao<Announcement>{
	public String findResultSize(Page page);
}
