package cn.forgeeks.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.CollegeDao;
import cn.forgeeks.domain.College;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.CollegeService;

@Repository
public class CollegeServiceImpl implements CollegeService {

	@Resource
	CollegeDao collegeDao;

	@Override
	public List<College> list(Map paraMap) {
		return collegeDao.find(paraMap);
	}

	@Override
	public List<College> findPage(Page page) {
		return collegeDao.findPage(page);
	}

	@Override
	public List<College> find(Map paraMap) {
		return collegeDao.find(paraMap);
	}

	@Override
	public College get(Serializable id) {
		return collegeDao.get(id);
	}

	@Override
	public void insert(College factory) {
		collegeDao.insert(factory);
	}

	@Override
	public void update(College factory) {
		collegeDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		collegeDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		collegeDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {
		return collegeDao.findResultSize(page);
	}
}
