package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.vo.ContractVO;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月11日
 */
@Service
public class ContractServiceImpl implements ContractService {
	@Resource
	ContractDao contractDao;
	@Resource
	ContractProductDao contractProductDao;
	@Resource 
	ExtCproductDao extCproductDao;
	
	public List<Contract> findPage(Page page) {
		return contractDao.findPage(page);
	}

	public List<Contract> find(Map paraMap) {
		return contractDao.find(paraMap);
	}

	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	public void insert(Contract contract) {
		contract.setId(UUID.randomUUID().toString());
		contract.setState(0);					//0草稿1已上报
		contractDao.insert(contract);
	}

	public void update(Contract contract) {
		contractDao.update(contract);
	}

	public void deleteById(Serializable id) {
		Serializable[] ids={id};
		contractDao.deleteById(id);
	}

	public void delete(Serializable[] ids) {
		extCproductDao.deleteByContractId(ids);
		contractProductDao.deleteByContractById(ids);
		contractDao.delete(ids);
	}

	public void submit(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 1);				//1已上报
		map.put("ids", ids);
		
		contractDao.updateState(map);
	}

	public void cancel(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 0);				//0草稿
		map.put("ids", ids);
		
		contractDao.updateState(map);
	}

	@Override
	public List<Contract> findSumByContractId() {
		return contractDao.findSumByContractId();
	}

	@Override
	public List<Contract> findAllByContractId() {
		return contractDao.findAllByContractId();
	}

	@Override
	public List<ContractVO> view(String contractId) {
		contractDao.view(contractId);
		return null;
	}
	
	

}
