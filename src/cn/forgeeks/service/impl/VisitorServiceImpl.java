package cn.forgeeks.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.VisitorDao;
import cn.forgeeks.domain.Visitor;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.VisitorService;

@Repository
public class VisitorServiceImpl implements VisitorService{
	
	@Resource 
	VisitorDao visitorDao;
	@Override
	public List<Visitor> list(Map paraMap) {
		return visitorDao.find(paraMap);
	}

	@Override
	public List<Visitor> findPage(Page page) {
		return visitorDao.findPage(page);
	}

	@Override
	public List<Visitor> find(Map paraMap) {
		return visitorDao.find(paraMap);
	}

	@Override
	public Visitor get(Serializable id) {
		return visitorDao.get(id);
	}

	@Override
	public void insert(Visitor factory) {
		visitorDao.insert(factory);
	}

	@Override
	public void update(Visitor factory) {
		visitorDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		visitorDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		visitorDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {
		return visitorDao.findResultSize(page);
	}
	
}
