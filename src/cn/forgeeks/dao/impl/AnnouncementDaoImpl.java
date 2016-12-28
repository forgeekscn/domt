package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.AnnouncementDao;
import cn.forgeeks.dao.StudentDao;
import cn.forgeeks.domain.Announcement;
import cn.forgeeks.domain.Student;

@Repository
public class AnnouncementDaoImpl extends BaseDaoImpl<Announcement> implements AnnouncementDao {

	public AnnouncementDaoImpl() {
		super.setNs("cn.forgeeks.mapper.AnnouncementMapper");
	}

}
