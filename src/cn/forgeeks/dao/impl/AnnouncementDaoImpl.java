package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.AnnouncementDao;
import cn.forgeeks.dao.StudentDao;
import cn.forgeeks.domain.Announcement;
import cn.forgeeks.domain.Student;
import cn.forgeeks.pagination.Page;

@Repository
public class AnnouncementDaoImpl extends BaseDaoImpl<Announcement> implements AnnouncementDao {

	public AnnouncementDaoImpl() {
		super.setNs("cn.forgeeks.mapper.AnnouncementMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
