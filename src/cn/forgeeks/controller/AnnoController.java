package cn.forgeeks.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Anno;
import cn.forgeeks.service.AnnoService;

@Controller
public class AnnoController {

	@Resource 
	AnnoService annoService;
	
	@RequestMapping(value = { "/anno/list.action" })
	public String studentlist(Map paraMap, Model model) {
		List<Anno> dataList=annoService.list(paraMap);
		System.out.println(dataList.size());
		return "/anno/list.jsp";
	}

}
