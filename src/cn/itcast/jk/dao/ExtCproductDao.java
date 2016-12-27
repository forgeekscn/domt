package cn.itcast.jk.dao;

import java.io.Serializable;

import cn.itcast.jk.domain.ExtCproduct;

/**
 * @Description:
 * @Author:	hec
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月9日
 */
public interface ExtCproductDao extends BaseDao<ExtCproduct> {
	public void deleteByContractProductById(Serializable[] ids);
	public void deleteByContractId(Serializable[] ids);
	
}
