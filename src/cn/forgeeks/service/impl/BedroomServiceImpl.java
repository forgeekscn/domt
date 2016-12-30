package cn.forgeeks.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.BedroomDao;
import cn.forgeeks.domain.Bedroom;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.BedroomService;

@Repository
public class BedroomServiceImpl implements BedroomService {

	@Resource
	BedroomDao bedroomDao;

	@Override
	public List<Bedroom> list(Map paraMap) {
		return bedroomDao.find(paraMap);
	}

	@Override
	public List<Bedroom> findPage(Page page) {
		return bedroomDao.findPage(page);
	}

	@Override
	public List<Bedroom> find(Map paraMap) {
		return bedroomDao.find(paraMap);
	}

	@Override
	public Bedroom get(Serializable id) {
		return bedroomDao.get(id);
	}

	@Override
	public void insert(Bedroom factory) {
		bedroomDao.insert(factory);
	}

	@Override
	public void update(Bedroom factory) {
		bedroomDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		bedroomDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		bedroomDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {

		return bedroomDao.findResultSize(page);
	}

}
