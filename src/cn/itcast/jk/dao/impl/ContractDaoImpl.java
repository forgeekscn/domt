package cn.itcast.jk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements
		ContractDao {
	public ContractDaoImpl() {
		// 设置命名空间
		super.setNs("cn.itcast.jk.mapper.ContractMapper");
	}

	public void updateState(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateState", map);
	}

	@Override
	public List<Contract> findSumByContractId() {
		return super.getSqlSession().selectList(super.getNs() + ".findSumByContractId");
	}

	@Override
	public List<Contract> findAllByContractId() {
		return super.getSqlSession().selectList(super.getNs() + ".findAllByContractId");
	}

	@Override
	public List<ContractVO> view(String contractId) {
		return super.getSqlSession().selectList(super.getNs() + ".view");
	}
}
