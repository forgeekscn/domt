package cn.forgeeks.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Apartment;
import cn.forgeeks.domain.Bedroom;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ApartmentService;
import cn.forgeeks.service.BedroomService;

@Controller
public class BedroomController {

	@Resource
	BedroomService bedroomService;
	@Resource
	ApartmentService apartmentService;
	
	@RequestMapping("/br/list.action")
	public String annolist(String totalPage,Integer pageNo,Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();

		Page page= new Page();
		page.setParams(map);
		if(pageNo==null) pageNo=1;
		else if(pageNo<1) pageNo=1;
		
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
		
		List<Bedroom> dataList=bedroomService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/br/list.jsp";
	}
	public void getTotalPage(Page page){
		String s=bedroomService.findResultSize(page);
		if(s==null) page.setTotalRecord(0); 
		else page.setTotalRecord(Integer.valueOf(s));
	}
	@RequestMapping("/br/tocreate.action")
	public String tocreate(Model model) {
		List<Apartment> sList=apartmentService.list(null);
		model.addAttribute("sList",sList);
		return "/br/create.jsp";
	}
	@RequestMapping("/br/create.action")
	public String create(Bedroom br, Model model) {
		br.setBedroomId(UUID.randomUUID().toString().substring(0, 8));
		br.setStatus("N");
		bedroomService.insert(br);
		return "redirect:/br/list.action";
	}
	
	@RequestMapping("/br/update.action")
	public String update(Bedroom br, Model model) {
		bedroomService.update(br);
		return "redirect:/br/list.action";
	}
	
	@RequestMapping("/br/toupdate.action")
	public String toupdate(String brId, Model model) {
		List<Apartment> sList=apartmentService.list(null);
		model.addAttribute("sList",sList);
		Bedroom anno = bedroomService.get(brId);
		model.addAttribute("obj", anno);
		return "/br/update.jsp";
	}

	@RequestMapping("/br/deletebyid.action")
	public String deletebyid(String pageNo,String totalPage,String brId, Model model) throws NumberFormatException, UnsupportedEncodingException {
		bedroomService.deleteById(brId);
		return annolist(totalPage,Integer.valueOf(pageNo), model);
	}

	@RequestMapping("/br/delete.action")
	public String delete(String pageNo,String totalPage,String sb, String msg,Model model) throws NumberFormatException, UnsupportedEncodingException {
		String[] ids=sb.split(",");
		bedroomService.delete(ids);
		return annolist(totalPage, Integer.valueOf(pageNo), model);
	}
}
