package cn.itcast.jk.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ExtCproduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月9日
 */
@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao{
	public ExtCproductDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.ExtCproductMapper");
	}

	public void deleteByContractProductById(Serializable[] ids){
		super.getSqlSession().delete(super.getNs()+".deleteByContractProductById",ids);
	}

	@Override
	public void deleteByContractId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractId",ids);
		
	}
}
