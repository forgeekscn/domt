package cn.forgeeks.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.ManagerDao;
import cn.forgeeks.domain.Manager;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ManagerService;

@Repository
public class ManagerServiceImpl implements ManagerService{
	
	@Resource 
	ManagerDao studentDao;
	@Override
	public List<Manager> list(Map paraMap) {
		return studentDao.find(paraMap);
	}

	@Override
	public List<Manager> findPage(Page page) {
		return studentDao.findPage(page);
	}

	@Override
	public List<Manager> find(Map paraMap) {
		return studentDao.find(paraMap);
	}

	@Override
	public Manager get(Serializable id) {
		return studentDao.get(id);
	}

	@Override
	public void insert(Manager factory) {
		studentDao.insert(factory);
	}

	@Override
	public void update(Manager factory) {
		studentDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		studentDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		studentDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {
		return studentDao.findResultSize(page);
	}
	
}
