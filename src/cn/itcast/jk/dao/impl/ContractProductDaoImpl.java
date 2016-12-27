package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月9日
 */
@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao{
	public ContractProductDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.ContractProductMapper");
	}

	@Override
	public void deleteByContractById(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractById",ids);
	}


}
