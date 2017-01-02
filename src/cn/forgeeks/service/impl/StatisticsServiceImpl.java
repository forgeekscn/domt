package cn.forgeeks.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.StatisticsDao;
import cn.forgeeks.service.StatisticsService;

@Repository
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	StatisticsDao statisticsDao;

	@Override
	public void updateStuBrByCla(Map map) {
		statisticsDao.updateStuBrByCla(map);
	}

}
