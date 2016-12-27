package cn.itcast.jk.controller.cargo.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.FactoryService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月11日
 */
@Controller
public class ContractProductController extends BaseController {
	@Resource
	ContractProductService contractProductService;
	@Resource
	FactoryService factoryService;

	
	// 转向新增页面
	@RequestMapping("/cargo/contractproduct/tocreate.action")
	public String tocreate(String contractId, Model model) { // 传递主表的ID
		model.addAttribute("contractId", contractId);
		
		// 准备生产厂家的下拉列表
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);

		// 某个合同下的货物列表
		Map paraMap = new HashMap();
		paraMap.put("contractId", contractId);

		List<ContractProduct> dataList = contractProductService.find(paraMap);
		model.addAttribute("dataList", dataList);

		return "/cargo/contract/jContractProductCreate.jsp"; // 货物的新增页面
	}

	// 新增
	@RequestMapping("/cargo/contractproduct/insert.action")
	public String insert(ContractProduct contractProduct, Model model) {
		contractProductService.insert(contractProduct);

		model.addAttribute("contractId", contractProduct.getContractId()); // 传递主表的ID

		return "redirect:/cargo/contractproduct/tocreate.action"; // 新增完转向新增页面-批量新增
	}

	// 转向修改页面
	@RequestMapping("/cargo/contractproduct/toupdate.action")
	public String toupdate(String id, String contractId, Model model) {
		model.addAttribute("contractId", contractId);
		
		// 准备生产厂家的下拉列表
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);

		ContractProduct contractProduct = contractProductService.get(id);
		model.addAttribute("obj", contractProduct);
		return "/cargo/contract/jContractProductUpdate.jsp"; //
	}

	// 修改
	@RequestMapping("/cargo/contractproduct/update.action")
	public String update(ContractProduct contractProduct) {
		contractProductService.update(contractProduct);
		return "redirect:/cargo/contractproduct/tocreate.action"
				+ "?contractId=" + contractProduct.getContractId(); // 新增完转向新增页面-批量新增
	}

	// 删除
	@RequestMapping("/cargo/contractproduct/deletebyid.action")
	public String delete(String id, String contractId) {
		contractProductService.deleteById(id);
		return "redirect:/cargo/contractproduct/tocreate.action"
				+ "?contractId=" + contractId; // 新增完转向新增页面-批量新增
	}
	
	
	
	

}
