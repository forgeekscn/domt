package cn.forgeeks.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Student;
import cn.forgeeks.service.StudentService;

@Controller
public class StudentController {

	@Resource 
	StudentService studentService;
	
	@RequestMapping(value = { "/student/list.action" })
	public String studentlist() {
		List<Student> dataList=studentService.list();
		System.out.println(dataList.size());
		return "/index.jsp";
	}

}
