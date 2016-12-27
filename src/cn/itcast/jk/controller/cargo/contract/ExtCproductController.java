package cn.itcast.jk.controller.cargo.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.jk.service.FactoryService;

@Controller
public class ExtCproductController extends BaseController {
	@Resource
	ExtCproductService extCproductService;
	@Resource
	ContractProductService contractProductService;
	@Resource
	FactoryService factoryService;
	
	@RequestMapping("/factory.action")
	public void f1(){
		List<Factory> list=factoryService.getFactoryList();
		for(Factory f:list){
			System.out.println(f.getFactoryName());
		}
	}
	
	@RequestMapping("/cargo/extcproduct/tocreate.action")
	public String tocreate(String contractProductId,Model model){
		model.addAttribute("contractProductId", contractProductId);
		Map paraMap= new HashMap();
		paraMap.put("contractProductId", contractProductId);
		List<ExtCproduct> dataList= extCproductService.find(paraMap);
		model.addAttribute("dataList",dataList);
		List<Factory> factoryList=factoryService.getFactoryList();
		model.addAttribute("factoryList",factoryList);
		return "/cargo/contract/jExtCproductCreate.jsp";
	}
	@RequestMapping("/cargo/extcproduct/insert.action")
	public String insert(ExtCproduct extCproduct,Model model){
		extCproductService.insert(extCproduct);
		model.addAttribute("contractProductId",extCproduct.getContractProductId());
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	@RequestMapping("/cargo/extcproduct/toupdate.action")
	public String toupdate(String id,String contractProductId,Model model){
		model.addAttribute("id",id);
		model.addAttribute("contractProductId",contractProductId);
		model.addAttribute("obj",extCproductService.get(id));
		List<Factory> factoryList= factoryService.getFactoryList();
		model.addAttribute("factoryList",factoryList);
		return "/cargo/contract/jExtCproductUpdate.jsp";
	}
	@RequestMapping("/cargo/extcproduct/update.action")
	public String update(ExtCproduct extCproduct,Model model){
		model.addAttribute("contractProductId",extCproduct.getContractProductId());
		extCproductService.update(extCproduct);
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	@RequestMapping("/cargo/extcproduct/deletebyid.action")
	public String deletebyid(String id,String contractProductId,Model model){
		model.addAttribute("contractProductId",contractProductId);
		extCproductService.deleteById(id);
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	

}