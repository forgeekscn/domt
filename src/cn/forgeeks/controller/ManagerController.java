package cn.forgeeks.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Apartment;
import cn.forgeeks.domain.College;
import cn.forgeeks.domain.Manager;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ApartmentService;
import cn.forgeeks.service.BedroomService;
import cn.forgeeks.service.ClassesService;
import cn.forgeeks.service.CollegeService;
import cn.forgeeks.service.ManagerService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class ManagerController {

	@Resource 
	ManagerService managerService;
	@Resource 
	CollegeService collegeService;
	@Resource 
	ClassesService classService;
	@Resource 
	ApartmentService apartmentService;

	@Resource
	BedroomService bedroomService;
	
	@RequestMapping("/man/list.action")
	public String annolist(String arg,String key,String totalPage,Integer pageNo,Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();
		
		if(UtilFuns.isEmpty(arg)) map.put("arg", null); else map.put("arg", arg);
		if(UtilFuns.isNotEmpty(key)) key=URLDecoder.decode(key, "UTF-8");	else key=null;

		map.put("key","%"+key+"%");
		
		model.addAttribute("arg",arg);
		model.addAttribute("key",key);
		
		Page page= new Page();
		page.setParams(map);
		if(pageNo==null) pageNo=1;else if(pageNo<1) pageNo=1;
		page.setPageNo(pageNo);
		page.setPageSize(5);
		if(pageNo==1) {
			getTotalPage(page);
			if(  page.getTotalRecord()*1.0/page.getPageSize()-
				 page.getTotalRecord()/page.getPageSize() <0.000001 )
				page.setTotalPage(page.getTotalRecord()/page.getPageSize());
			else {
					page.setTotalPage(page.getTotalRecord()/page.getPageSize()+1);
			}
		}
		else page.setTotalPage(Integer.valueOf(totalPage));
		model.addAttribute("page",page);
		
		List<Manager> dataList=managerService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/man/list.jsp";
	}
	public void getTotalPage(Page page){
		String s=managerService.findResultSize(page);
		if(s==null) page.setTotalRecord(0); 
		else page.setTotalRecord(Integer.valueOf(s));
	}
	@RequestMapping("/man/tocreate.action")
	public String tocreate(Model model) {
		List<College> sList1=collegeService.find(null);
		model.addAttribute("sList1",sList1);
		return "/man/create.jsp";
	}
	@RequestMapping("/man/create.action")
	public String create(Manager man, Model model) {
		man.setManagerId(UUID.randomUUID().toString().substring(0, 8));
		managerService.insert(man);
		return "redirect:/man/list.action";
	}
	
	@RequestMapping("/man/update.action")
	public String update(Manager man, Model model) {
		managerService.update(man);
		return "redirect:/man/list.action";
	}
	
	@RequestMapping("/man/toupdate.action")
	public String toupdate(String manId, Model model) {
		return "/man/update.jsp";
	}

	@RequestMapping("/man/deletebyid.action")
	public String deletebyid(String pageNo,String totalPage,String manId, Model model) throws NumberFormatException, UnsupportedEncodingException {
		managerService.deleteById(manId);
		return annolist(null,null,totalPage,Integer.valueOf(pageNo), model);
	}

	@RequestMapping("/man/delete.action")
	public String delete(String pageNo,String totalPage,String sb, String msg,Model model) throws NumberFormatException, UnsupportedEncodingException {
		String[] ids=sb.split(",");
		managerService.delete(ids);
		return annolist(null,null,totalPage, Integer.valueOf(pageNo), model);
	}

}
