package cn.forgeeks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Announcement;
import cn.forgeeks.domain.Student;
import cn.forgeeks.service.AnnouncementService;
import cn.forgeeks.service.StudentService;

@Controller
public class AnnouncementController {

	@Resource 
	AnnouncementService announcementService;
	
	@RequestMapping(value = { "/anno/list.action" })
	public String annolist(Model model) {
		Map map= new HashMap();
//		map.put("classId", classId);
		List<Announcement> dataList=announcementService.list(map);
		Announcement announcement=announcementService.get("0002");
//		announcementService.find(paraMap);
		
		System.out.println(dataList.size());
		return "/anno/list.jsp";
	}

}
