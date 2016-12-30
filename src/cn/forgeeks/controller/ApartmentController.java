package cn.forgeeks.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Apartment;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ApartmentService;

@Controller
public class ApartmentController {

	@Resource
	ApartmentService apartmentService;

	@RequestMapping("/apm/gettotalfloor.action")
	public String gettotalfloor(String apartmentId,Model model) throws Exception{
		String floor=apartmentService.get(apartmentId).getTotalFloor();
		JSONObject json=new JSONObject();
		json.put("status", "200");
		json.put("floor", floor);
		String data=json.toString();
		model.addAttribute("data",data);
		return "/apm/getdata.jsp";
	}
	
	
	@RequestMapping("/apm/list.action")
	public String apmlist(String totalPage,Integer pageNo,Model model) throws UnsupportedEncodingException {
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
		
		List<Apartment> dataList=apartmentService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/apm/list.jsp";
	}
  public void getTotalPage(Page page){
		String s=apartmentService.findResultSize(page);
		if(s==null) page.setTotalRecord(0); 
		else page.setTotalRecord(Integer.valueOf(apartmentService.findResultSize(page)));
	}

	@RequestMapping("/apm/tocreate.action")
	public String tocreate(Model model) {
		return "/apm/create.jsp";
	}

	@RequestMapping("/apm/create.action")
	public String create(Apartment apm, Model model) {
		apm.setApartmentId(UUID.randomUUID().toString().substring(0, 8));
		apartmentService.insert(apm);
		return "redirect:/apm/list.action";
	}
	@RequestMapping("/apm/toupdate.action")
	public String toupdate(String apmId, Model model) {
		Apartment apm = apartmentService.get(apmId);
		model.addAttribute("obj", apm);
		return "/apm/update.jsp";
	}
	@RequestMapping("/apm/update.action")
	public String update(Apartment apm, Model model) {
		apartmentService.update(apm);
		return "redirect:/apm/list.action";
	}
	
	@RequestMapping("/apm/deletebyid.action")
	public String deletebyid(String pageNo,String totalPage,String apmId, Model model) 
			throws NumberFormatException, UnsupportedEncodingException {
		apartmentService.deleteById(apmId);
		return apmlist(totalPage, Integer.valueOf(pageNo), model);
	}
	
	@RequestMapping("/apm/delete.action")
	public String delete(String pageNo,String totalPage,String sb, String msg,Model model) throws NumberFormatException, UnsupportedEncodingException {
		String[] ids=sb.split(",");
		apartmentService.delete(ids);
		return apmlist(totalPage,Integer.valueOf(pageNo), model);
	}
	/*	




*/
}
