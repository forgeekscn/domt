package cn.forgeeks.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Classes;
import cn.forgeeks.domain.College;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.CollegeService;

@Controller
public class CollegeController {

	@Resource
	CollegeService collegeService;

 	@RequestMapping("/cl/getdata.action")
	public String getdata(Model model){
 		List<College> list = collegeService.find(null);
 		JSONArray jsonArray=new JSONArray();
 		jsonArray.addAll(list);
 		String data=jsonArray.toString();
 		System.out.println(data);
 		model.addAttribute("data",data);
 		return "/cla/getdata.jsp";
 	}
 	
	@RequestMapping("/cl/list.action")
	public String cllist(String totalPage,Integer pageNo,Model model) throws UnsupportedEncodingException {
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
		
		List<College> dataList=collegeService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/cl/list.jsp";
	}
  public void getTotalPage(Page page){
		String s=collegeService.findResultSize(page);
		if(s==null) page.setTotalRecord(0); 
		else page.setTotalRecord(Integer.valueOf(collegeService.findResultSize(page)));
	}

 	@RequestMapping("/cl/tocreate.action")
	public String tocreate(Model model) {
		return "/cl/create.jsp";
	}

	@RequestMapping("/cl/create.action")
	public String create(College cl, Model model) {
		cl.setCollegeId(UUID.randomUUID().toString().substring(0, 8));
		collegeService.insert(cl);
		return "redirect:/cl/list.action";
	}
	@RequestMapping("/cl/toupdate.action")
	public String toupdate(String clId, Model model) {
		College cl = collegeService.get(clId);
		model.addAttribute("obj", cl);
		return "/cl/update.jsp";
	}
	@RequestMapping("/cl/update.action")
	public String update(College cl, Model model) {
		collegeService.update(cl);
		return "redirect:/cl/list.action";
	}
	
	@RequestMapping("/cl/deletebyid.action")
	public String deletebyid(String pageNo,String totalPage,String clId, Model model) 
			throws NumberFormatException, UnsupportedEncodingException {
		collegeService.deleteById(clId);
		return cllist(totalPage, Integer.valueOf(pageNo), model);
	}
	
	@RequestMapping("/cl/delete.action")
	public String delete(String pageNo,String totalPage,String sb, String msg,Model model) throws NumberFormatException, UnsupportedEncodingException {
		String[] ids=sb.split(",");
		collegeService.delete(ids);
		return cllist(totalPage,Integer.valueOf(pageNo), model);
	}
  
}
