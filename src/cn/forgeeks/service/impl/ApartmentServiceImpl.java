package cn.forgeeks.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.forgeeks.dao.ApartmentDao;
import cn.forgeeks.domain.Apartment;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ApartmentService;

@Repository
public class ApartmentServiceImpl implements ApartmentService {

	@Resource
	ApartmentDao apartmentDao;

	@Override
	public List<Apartment> list(Map paraMap) {
		return apartmentDao.find(paraMap);
	}

	@Override
	public List<Apartment> findPage(Page page) {
		return apartmentDao.findPage(page);
	}

	@Override
	public List<Apartment> find(Map paraMap) {
		return apartmentDao.find(paraMap);
	}

	@Override
	public Apartment get(Serializable id) {
		return apartmentDao.get(id);
	}

	@Override
	public void insert(Apartment factory) {
		apartmentDao.insert(factory);
	}

	@Override
	public void update(Apartment factory) {
		apartmentDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		apartmentDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		apartmentDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {
		return apartmentDao.findResultSize(page);
	}
}
