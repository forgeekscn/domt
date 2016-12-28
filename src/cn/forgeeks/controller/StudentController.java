package cn.forgeeks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Student;
import cn.forgeeks.service.StudentService;

@Controller
public class StudentController {

	@Resource 
	StudentService studentService;
	
	@RequestMapping(value = { "/student/list.action" })
	public String studentlist(String classId,Model model) {
		Map map= new HashMap();
		map.put("classId", classId);
		List<Student> dataList=studentService.list(map);
		Student student= studentService.get("00001");
		System.out.println(dataList.size());
		return "redirect:/home.action";
	}

}
