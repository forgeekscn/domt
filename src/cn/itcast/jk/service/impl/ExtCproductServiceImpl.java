package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.util.UtilFuns;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月11日
 */
@Service
public class ExtCproductServiceImpl implements ExtCproductService {
	@Resource
	ExtCproductDao extCproductDao;

	public List<ExtCproduct> findPage(Page page) {
		return extCproductDao.findPage(page);
	}

	public List<ExtCproduct> find(Map paraMap) {
		return extCproductDao.find(paraMap);
	}

	public ExtCproduct get(Serializable id) {
		return extCproductDao.get(id);
	}

	public void insert(ExtCproduct extCproduct) {
		extCproduct.setId(UUID.randomUUID().toString());
		// 自动计算总金额=数量*单价 ...修改，删除；同步合同总金额
		if (UtilFuns.isNotEmpty(extCproduct.getPrice())	&& UtilFuns.isNotEmpty(extCproduct.getCnumber())) {
			extCproduct.setAmount(extCproduct.getCnumber()* extCproduct.getPrice());
		}
		extCproductDao.insert(extCproduct);
	}

	public void update(ExtCproduct extCproduct) {
		extCproductDao.update(extCproduct);
	}

	public void deleteById(Serializable id) {
		
		extCproductDao.deleteById(id);
	}

	public void delete(Serializable[] ids) {
		extCproductDao.delete(ids);
	}

}
