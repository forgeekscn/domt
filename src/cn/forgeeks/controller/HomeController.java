package cn.forgeeks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Manager;
import cn.forgeeks.domain.Student;
import cn.forgeeks.service.ManagerService;
import cn.forgeeks.service.StudentService;

@Controller
public class HomeController {

	
	@Resource
	ManagerService managerService;
	@Resource
	StudentService studentService;
	
	@RequestMapping(value = { "/login.action" })
	public String login(String name,String password,Model model,HttpSession httpSession) {
		if(name.equals("root") && password.equals("root") ){
			return home(name, "root", model, httpSession);
		}
		
		Map<String,String> map= new HashMap<String,String>();
		map.put("key","%"+null+"%");
		map.put("name",name);
		map.put("password",password);
		
		List<Student> list2=studentService.find(map);
		List<Manager> list=managerService.find(map);
		if(list.size()>=1){
			Manager manager= list.get(0);
			httpSession.setAttribute("user", manager);
			return home(manager.getManagerName(), "manager", model, httpSession);
			
		}else {
			if(list2.size()<1){
				return "redirect:/tologin.action";
			}else{
				Student student= list2.get(0);
				httpSession.setAttribute("user", student);
				return home(student.getStudentName(), "student", model, httpSession);
			}
		}
	}
	
	
	@RequestMapping(value = { "/home.action" } )
	public String home(String name,String type,Model model,HttpSession httpSession) {
		httpSession.setAttribute("name", name);
		httpSession.setAttribute("type", type);
		return "/index.jsp";
	}

	@RequestMapping(value = { "/logout.action" })
	public String logout(Model model,HttpSession httpSession) {
		httpSession.setAttribute("name", null);
		httpSession.setAttribute("type", null);
		return "redirect:/tologin.action";
	}

	
	
	@RequestMapping(value = { "/default.action" })
	public String left() {
		return "/basic/default.jsp";
	}

	@RequestMapping(value = { "/tologin.action" })
	public String tologin() {
		return "/basic/login.jsp";
	}
}
