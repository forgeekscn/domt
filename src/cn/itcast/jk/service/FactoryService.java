package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.pagination.Page;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月9日
 */
public interface FactoryService {
	public List<Factory> findPage(Page page);		//分页查询
	public List<Factory> find(Map paraMap);			//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public Factory get(Serializable id);			//只查询一个，常用于修改
	public void insert(Factory factory);			//插入，用实体作为参数
	public void update(Factory factory);			//修改，用实体作为参数
	public void deleteById(Serializable id);		//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);			//批量删除；支持整数型和字符串类型ID
	
	public void start(Serializable[] ids);			//启用
	public void stop(Serializable[] ids);			//停用
	
	public List<Factory> getFactoryList();			//获取生产厂家列表
}
