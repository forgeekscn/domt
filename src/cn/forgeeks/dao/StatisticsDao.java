package cn.forgeeks.dao;

import java.util.Map;

import cn.forgeeks.domain.Statistics;

public interface StatisticsDao extends BaseDao<Statistics> {

	public void updateStuBrByCla(Map map); 

}
