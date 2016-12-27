package cn.itcast.jk.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月9日
 */
public interface ContractDao extends BaseDao<Contract> {
	public void updateState(Map map);			//修改状态
	public List<Contract>  findSumByContractId();
	public List<Contract>  findAllByContractId();
	public List<ContractVO> view(String contractId);
}
