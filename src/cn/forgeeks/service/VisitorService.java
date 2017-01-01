package cn.forgeeks.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.forgeeks.domain.Visitor;
import cn.forgeeks.pagination.Page;

public interface VisitorService {

	public List<Visitor> list(Map paraMap);

	public List<Visitor> findPage(Page page); // 分页查询

	public List<Visitor> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public Visitor get(Serializable id); // 只查询一个，常用于修改

	public void insert(Visitor factory); // 插入，用实体作为参数

	public void update(Visitor factory); // 修改，用实体作为参数

	public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID

	public String findResultSize(Page page); // 分页查询总记录数

}
