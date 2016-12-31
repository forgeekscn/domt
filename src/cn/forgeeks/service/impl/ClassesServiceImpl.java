package cn.forgeeks.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.ClassesDao;
import cn.forgeeks.domain.Classes;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ClassesService;

@Repository
public class ClassesServiceImpl implements ClassesService {

	@Resource
	ClassesDao classesDao;

	@Override
	public List<Classes> list(Map paraMap) {
		return classesDao.find(paraMap);
	}

	@Override
	public List<Classes> findPage(Page page) {
		return classesDao.findPage(page);
	}

	@Override
	public List<Classes> find(Map paraMap) {
		return classesDao.find(paraMap);
	}

	@Override
	public Classes get(Serializable id) {
		return classesDao.get(id);
	}

	@Override
	public void insert(Classes factory) {
		classesDao.insert(factory);
	}

	@Override
	public void update(Classes factory) {
		classesDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		classesDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		classesDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {
		return classesDao.findResultSize(page);
	}
}
