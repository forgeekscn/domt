package cn.forgeeks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// 系统首页模块

	@RequestMapping(value = { "/home.action" })
	public String home() {

		return "/index.jsp";
	}

	@RequestMapping(value = { "/default.action" })
	public String left() {
		return "/basic/default.jsp";
	}

	@RequestMapping(value = { "/tologin.action" })
	public String tologin() {
		return "/basic/login.jsp";
	}

	@RequestMapping(value = { "/login.action" })
	public String login(Model model) {
		System.out.println("root login!");
		return "redirect:/home.action";
	}
	
}
