package cn.forgeeks.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.AnnoDao;
import cn.forgeeks.domain.Anno;
import cn.forgeeks.service.AnnoService;

@Repository
public class AnnoServiceImpl implements AnnoService{
	
	@Resource 
	AnnoDao annoDao;
	
	public  List<Anno> list(Map paraMap){
		return annoDao.find(paraMap);
	}
	
	
}
