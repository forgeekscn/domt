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
import org.springframework.web.bind.annotation.RequestParam;

import cn.forgeeks.domain.Announcement;
import cn.forgeeks.service.AnnouncementService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class AnnouncementController {

	@Resource
	AnnouncementService announcementService;

	@RequestMapping("/anno/list.action")
	public String annolist(String date,String key,Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();
		if(UtilFuns.isNotEmpty(date)){
			map.put("date", date);
		}
//		 new String(zhongwen.getBytes("iso8859-1"),"GBK");
		if(key!=null) key=URLDecoder.decode(key, "UTF-8");
		map.put("key", "%"+key+"%");
		List<Announcement> dataList = announcementService.list(map);
		model.addAttribute("dataList", dataList);
		return "/anno/list.jsp";
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
	public String deletebyid(String annoId, Model model) {
		announcementService.deleteById(annoId);
		return "redirect:/anno/list.action";
	}

	@RequestMapping("/anno/delete.action")
	public String delete(String sb, String msg,Model model) {
		String[] ids=sb.split(",");
		announcementService.delete(ids);
		return "redirect:/anno/list.action";
	}

}
