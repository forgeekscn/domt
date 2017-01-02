package cn.forgeeks.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.StatisticsDao;
import cn.forgeeks.domain.Statistics;

@Repository
public class StatisticsDaoImpl extends BaseDaoImpl<Statistics> implements StatisticsDao {

	public StatisticsDaoImpl() {
		super.setNs("cn.forgeeks.mapper.StatisMapper");
	}

	@Override
	public void updateStuBrByCla(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStuBrByCla");		
	}


}
