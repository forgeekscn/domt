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

import cn.forgeeks.domain.College;
import cn.forgeeks.domain.Visitor;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ApartmentService;
import cn.forgeeks.service.VisitorService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class VisitorController {

	@Resource
	ApartmentService apartmentService;
	@Resource
	VisitorService visitorService;


	@RequestMapping("/visit/listforstu.action")
	public String listforstu(String arg2,Model model){
		Map map = new HashMap();
		if (UtilFuns.isEmpty(arg2)) arg2 = null;
		map.put("arg2", arg2);
		List<Visitor> dataList=visitorService.find(map);
		model.addAttribute("dataList",dataList);
		
		return "/visit/list.jsp";
	}
	
	@RequestMapping("/visit/list.action")
	public String annolist(String arg1, String arg2, String key,
			String totalPage, Integer pageNo, Model model)
			throws UnsupportedEncodingException {
		Map map = new HashMap();

		if (UtilFuns.isEmpty(arg1))
			arg1 = null;
		if (UtilFuns.isEmpty(arg2))
			arg2 = null;
		if (UtilFuns.isNotEmpty(key))
			key = URLDecoder.decode(key, "UTF-8");
		else
			key = null;

		map.put("arg1", arg1);
		map.put("arg2", arg2);

		map.put("key", "%" + key + "%");

		model.addAttribute("arg1", arg1);
		model.addAttribute("arg2", arg2);
		model.addAttribute("key", key);

		Page page = new Page();
		page.setParams(map);
		if (pageNo == null)
			pageNo = 1;
		else if (pageNo < 1)
			pageNo = 1;

		page.setPageNo(pageNo);
		page.setPageSize(5);
		if (pageNo == 1) {
			getTotalPage(page);
			if (page.getTotalRecord() * 1.0 / page.getPageSize()
					- page.getTotalRecord() / page.getPageSize() < 0.000001)
				page.setTotalPage(page.getTotalRecord() / page.getPageSize());
			else {
				page.setTotalPage(page.getTotalRecord() / page.getPageSize()
						+ 1);
			}
		} else
			page.setTotalPage(Integer.valueOf(totalPage));
		model.addAttribute("page", page);

		List<Visitor> dataList = visitorService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/visit/list.jsp";
	}

	public void getTotalPage(Page page) {
		String s = visitorService.findResultSize(page);
		if (s == null)
			page.setTotalRecord(0);
		else
			page.setTotalRecord(Integer.valueOf(s));
	}

	@RequestMapping("/visit/tocreate.action")
	public String tocreate(Model model) {
		return "/visit/create.jsp";
	}

	@RequestMapping("/visit/create.action")
	public String create(Visitor visit, Model model) {
		visit.setVisitorId(UUID.randomUUID().toString().substring(0, 8));
		visitorService.insert(visit);
		return "redirect:/visit/list.action";
	}

	@RequestMapping("/visit/update.action")
	public String update(Visitor visit, Model model) {
		visitorService.update(visit);
		return "redirect:/visit/list.action";
	}

	@RequestMapping("/visit/toupdate.action")
	public String toupdate(String visitorId, Model model) {
		Visitor visitdent = visitorService.get(visitorId);
		model.addAttribute("obj", visitdent);

		return "/visit/update.jsp";
	}

	@RequestMapping("/visit/deletebyid.action")
	public String deletebyid(String pageNo, String totalPage, String visitId,
			Model model) throws NumberFormatException,
			UnsupportedEncodingException {
		visitorService.deleteById(visitId);
		return annolist(null, null, null, totalPage, Integer.valueOf(pageNo),
				model);
	}

	@RequestMapping("/visit/delete.action")
	public String delete(String pageNo, String totalPage, String sb,
			String msg, Model model) throws NumberFormatException,
			UnsupportedEncodingException {
		String[] ids = sb.split(",");
		visitorService.delete(ids);
		return annolist(null, null, null, totalPage, Integer.valueOf(pageNo),
				model);
	}

}
