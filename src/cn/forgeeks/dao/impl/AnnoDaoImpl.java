package cn.forgeeks.dao.impl;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.AnnoDao;
import cn.forgeeks.domain.Anno;

@Repository
public class AnnoDaoImpl extends BaseDaoImpl<Anno> implements AnnoDao{

	public AnnoDaoImpl(){
		super.setNs("cn.forgeeks.mapper.AnnoMapper");
	}
}
