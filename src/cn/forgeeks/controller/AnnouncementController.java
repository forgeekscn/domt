package cn.forgeeks.controller;

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

@Controller
public class AnnouncementController {

	@Resource
	AnnouncementService announcementService;

	@RequestMapping("/anno/list.action")
	public String annolist(Model model) {
		Map map = new HashMap();
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
	public String delete(String sb, String msg) {
		String[] ids=sb.split(",");
		announcementService.delete(ids);
		// meimaobing!!!sb zai 再把
		// sb应该有两个值吧 我知道 6666666
		return "redirect:/anno/list.action";
	}
	// 有请求是吧？恩 就是delete
	// qiantaizhinengzheyang ma
	// 再就是后台的毛病了
	// 你那个表dan的其他数据能够传过去吗能 你在前台指我看看 本来这个地方传值就有点麻烦 这里还要按springmvc规则来穿
	// 你这个完全不可不用表单传啊 利用ajax 怎么搞 就写js啊 你把想传的赋个class js获得就可以了

}
