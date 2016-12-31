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
import cn.forgeeks.service.ClassesService;
import cn.forgeeks.service.CollegeService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class ClassesController {

	@Resource
	ClassesService classesService;
	@Resource
	CollegeService collegeService;
	
 	@RequestMapping("/cla/getdata.action")
	public String getdata(String collegeId,Model model){
 		Map map= new HashMap();
 		map.put("collegeId",collegeId);
 		List<Classes> list = classesService.find(map);
 		
 		JSONArray jsonArray=new JSONArray();
 		jsonArray.addAll(list);
 		String data=jsonArray.toString();
 		System.out.println(data);
 		model.addAttribute("data",data);
 		return "/cla/getdata.jsp";
 	}
 	
	@RequestMapping("/cla/list.action")
	public String clalist(String arg,String key,String totalPage,Integer pageNo,Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();
		if(UtilFuns.isNotEmpty(arg)) arg=URLDecoder.decode(arg, "UTF-8");else arg=null;
		if(UtilFuns.isNotEmpty(key)) key=URLDecoder.decode(key, "UTF-8");
		map.put("key","%"+key+"%");
		map.put("arg", arg);
		model.addAttribute("arg",arg);
		model.addAttribute("key",key);
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
		
		List<Classes> dataList=classesService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/cla/list.jsp";
	}
  public void getTotalPage(Page page){
		String s=classesService.findResultSize(page);
		if(s==null) page.setTotalRecord(0); 
		else page.setTotalRecord(Integer.valueOf(classesService.findResultSize(page)));
	}

	@RequestMapping("/cla/tocreate.action")
	public String tocreate(String clId,Model model) {
		List<College> sList=collegeService.find(null);
		model.addAttribute("sList",sList);
		return "/cla/create.jsp";
	}

	@RequestMapping("/cla/create.action")
	public String create(Classes cla, Model model) {
		cla.setClassId(UUID.randomUUID().toString().substring(0, 8));
		switch (cla.getGrade()) {
		case "d1":
			cla.setGrade("大一");
			break;
		case "d2":
			cla.setGrade("大二");
			break;
		case "d3":
			cla.setGrade("大三");
			break;
		case "d4":
			cla.setGrade("大四");
			break;
		case "y1":
			cla.setGrade("研一");
			break;
		case "y2":
			cla.setGrade("研二");
			break;
		case "y3":
			cla.setGrade("研三");
			break;
		default:
			break;
		}
		classesService.insert(cla);
		return "redirect:/cla/list.action";
	}
	@RequestMapping("/cla/toupdate.action")
	public String toupdate(String claId, Model model) {
		Classes cla = classesService.get(claId);
		List<College> sList=collegeService.find(null);
		model.addAttribute("sList",sList);
		model.addAttribute("obj", cla);
		return "/cla/update.jsp";
	}
	@RequestMapping("/cla/update.action")
	public String update(Classes cla, Model model) {
		switch (cla.getGrade()) {
		case "d1":
			cla.setGrade("大一");
			break;
		case "d2":
			cla.setGrade("大二");
			break;
		case "d3":
			cla.setGrade("大三");
			break;
		case "d4":
			cla.setGrade("大四");
			break;
		case "y1":
			cla.setGrade("研一");
			break;
		case "y2":
			cla.setGrade("研二");
			break;
		case "y3":
			cla.setGrade("研三");
			break;
		default:
			break;
		}
		classesService.update(cla);
		return "redirect:/cla/list.action";
	}
	
	@RequestMapping("/cla/deletebyid.action")
	public String deletebyid(String pageNo,String totalPage,String claId, Model model) 
			throws NumberFormatException, UnsupportedEncodingException {
		classesService.deleteById(claId);
		return clalist(null,null,totalPage, Integer.valueOf(pageNo), model);
	}
	
	@RequestMapping("/cla/delete.action")
	public String delete(String pageNo,String totalPage,String sb, String msg,Model model) throws NumberFormatException, UnsupportedEncodingException {
		String[] ids=sb.split(",");
		classesService.delete(ids);
		return clalist(null,null,totalPage,Integer.valueOf(pageNo), model);
	}
	/*	




*/
}
