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

import cn.forgeeks.domain.Announcement;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.AnnouncementService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class AnnouncementController {

	@Resource
	AnnouncementService announcementService;

	@RequestMapping("/anno/list.action")
	public String annolist(String totalPage,String date,String key,Integer pageNo,Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();
		if(UtilFuns.isNotEmpty(date)){
			map.put("date", date);
		}
		if(key!=null) key=URLDecoder.decode(key, "UTF-8");
		map.put("key", "%"+key+"%");
		model.addAttribute("date",date);
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
		
		List<Announcement> dataList=announcementService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/anno/list.jsp";
	}
	public void getTotalPage(Page page){
		String s=announcementService.findResultSize(page);
		if(s==null) page.setTotalRecord(0); 
		else page.setTotalRecord(Integer.valueOf(announcementService.findResultSize(page)));
	}

	@RequestMapping("/anno/tocreate.action")
	public String tocreate(Model model) {
		return "/anno/create.jsp";
	}

	@RequestMapping("/anno/update.action")
	public String update(Announcement anno, Model model) {
		announcementService.update(anno);
		return "redirect:/anno/list.action";
	}

	@RequestMapping("/anno/toupdate.action")
	public String toupdate(String annoId, Model model) {
		Announcement anno = announcementService.get(annoId);
		model.addAttribute("obj", anno);
		return "/anno/update.jsp";
	}

	@RequestMapping("/anno/create.action")
	public String create(Announcement anno, Model model) {
		anno.setAnnouncementId(UUID.randomUUID().toString().substring(0, 8));
		announcementService.insert(anno);
		return "redirect:/anno/list.action";
	}

	@RequestMapping("/anno/deletebyid.action")
	public String deletebyid(String pageNo,String totalPage,String annoId, Model model) throws NumberFormatException, UnsupportedEncodingException {
		announcementService.deleteById(annoId);
		return annolist(totalPage, null, null, Integer.valueOf(pageNo), model);
	}

	@RequestMapping("/anno/delete.action")
	public String delete(String pageNo,String totalPage,String sb, String msg,Model model) throws NumberFormatException, UnsupportedEncodingException {
		String[] ids=sb.split(",");
		announcementService.delete(ids);
		return annolist(totalPage, null, null, Integer.valueOf(pageNo), model);
	}

}
