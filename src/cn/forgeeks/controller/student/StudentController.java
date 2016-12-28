package cn.forgeeks.controller.student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Student;
import cn.forgeeks.service.StudentService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class StudentController {

	@Resource
	StudentService studentService;

	@RequestMapping("/student/list.action")
	public String list(String classId,Model model) {
		if(UtilFuns.isEmpty(classId)){
			classId="11303";
		}
		Map paraMap= new HashMap();
		paraMap.put("classId", classId);
		List<Student> dataList=studentService.find(paraMap);
		model.addAttribute("dataList",dataList);
		return "/student/list.jsp";
	}
	
	@RequestMapping("/student/get.action")
	public String get(String studentId,Model model){
		if(UtilFuns.isEmpty(studentId)){
			studentId="001";
		}
		Student student=studentService.get(studentId);
		model.addAttribute("student",student);
		return "/student/profile.jsp";
	}
}
