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

import cn.forgeeks.domain.Apartment;
import cn.forgeeks.domain.College;
import cn.forgeeks.domain.Student;
import cn.forgeeks.pagination.Page;
import cn.forgeeks.service.ApartmentService;
import cn.forgeeks.service.BedroomService;
import cn.forgeeks.service.ClassesService;
import cn.forgeeks.service.CollegeService;
import cn.forgeeks.service.StudentService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class StudentController {

	@Resource
	StudentService studentService;
	@Resource
	CollegeService collegeService;
	@Resource
	ClassesService classService;
	@Resource
	ApartmentService apartmentService;

	@Resource
	BedroomService bedroomService;

	@RequestMapping("/stu/list.action")
	public String annolist(String status, String college, String grade,
			String sex, String arg, String key, String totalPage,
			Integer pageNo, Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();
		if (UtilFuns.isEmpty(arg))
			map.put("arg", null);
		else
			map.put("arg", arg);
		if (UtilFuns.isNotEmpty(key))
			key = URLDecoder.decode(key, "UTF-8");
		else
			key = null;

		if (UtilFuns.isEmpty(status))
			status = null;
		if (UtilFuns.isEmpty(college))
			college = null;
		if (UtilFuns.isNotEmpty(grade))
			grade = URLDecoder.decode(grade, "UTF-8");
		else
			grade = null;
		if (UtilFuns.isNotEmpty(sex))
			sex = URLDecoder.decode(sex, "UTF-8");
		else
			sex = null;

		map.put("status", status);
		map.put("college", college);
		map.put("grade", grade);
		map.put("sex", sex);

		map.put("key", "%" + key + "%");

		model.addAttribute("arg", arg);
		model.addAttribute("key", key);

		model.addAttribute("status", status);
		model.addAttribute("college", college);
		model.addAttribute("grade", grade);
		model.addAttribute("sex", sex);

		Page page = new Page();
		page.setParams(map);
		if (pageNo == null)
			pageNo = 1;
		else if (pageNo < 1)
			pageNo = 1;

		page.setPageNo(pageNo);
		page.setPageSize(5);
		if (pageNo == 1) {
			getTotalPage(page);
			if (page.getTotalRecord() * 1.0 / page.getPageSize()
					- page.getTotalRecord() / page.getPageSize() < 0.000001)
				page.setTotalPage(page.getTotalRecord() / page.getPageSize());
			else {
				page.setTotalPage(page.getTotalRecord() / page.getPageSize()
						+ 1);
			}
		} else
			page.setTotalPage(Integer.valueOf(totalPage));
		model.addAttribute("page", page);

		List<Student> dataList = studentService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/stu/list.jsp";
	}

	public void getTotalPage(Page page) {
		String s = studentService.findResultSize(page);
		if (s == null)
			page.setTotalRecord(0);
		else
			page.setTotalRecord(Integer.valueOf(s));
	}

	@RequestMapping("/stu/tocreate.action")
	public String tocreate(Model model) {
		List<College> sList1 = collegeService.find(null);
		model.addAttribute("sList1", sList1);
		return "/stu/create.jsp";
	}

	@RequestMapping("/stu/findbybedroomid.action")
	public String findbybedroomid(String bedroomId,String totalPage,	Integer pageNo, Model model) {
		Map paraMap= new HashMap();
		paraMap.put("bedroomId",bedroomId);
		List<Student> dataList=studentService.find(paraMap);
		model.addAttribute("dataList",dataList);
		return "/stu/list.jsp";
	}
	
	@RequestMapping("/stu/findbyclassid.action")
	public String findbyclassid(String classId,Model model) {
		Map paraMap= new HashMap();
		paraMap.put("classId",classId);
		List<Student> dataList=studentService.find(paraMap);
		model.addAttribute("dataList",dataList);
		return "/stu/list.jsp";
	}

	
	@RequestMapping("/stu/create.action")
	public String create(Student stu, Model model) {
		stu.setStudentId(UUID.randomUUID().toString().substring(0, 8));
		if (stu.getBedroomId() != null)
			stu.setStatus("Y");
		else
			stu.setStatus("N");
		studentService.insert(stu);
		return "redirect:/stu/list.action";
	}

	@RequestMapping("/stu/update.action")
	public String update(Student stu, Model model) {
		if (stu.getBedroomId() != null)
			stu.setStatus("Y");
		studentService.update(stu);
		return "redirect:/stu/list.action";
	}

	@RequestMapping("/stu/toupdate.action")
	public String toupdate(String stuId, Model model) {
		List<College> sList1 = collegeService.find(null);
		model.addAttribute("sList1", sList1);
		Student student = studentService.get(stuId);
		model.addAttribute("obj", student);

		model.addAttribute("sList2", bedroomService.find(null));

		return "/stu/update.jsp";
	}

	@RequestMapping("/stu/deletebyid.action")
	public String deletebyid(String pageNo, String totalPage, String stuId,
			Model model) throws NumberFormatException,
			UnsupportedEncodingException {
		studentService.deleteById(stuId);
		return annolist(null, null, null, null, null, null, totalPage,
				Integer.valueOf(pageNo), model);
	}

	@RequestMapping("/stu/delete.action")
	public String delete(String pageNo, String totalPage, String sb,
			String msg, Model model) throws NumberFormatException,
			UnsupportedEncodingException {
		String[] ids = sb.split(",");
		studentService.delete(ids);
		return annolist(null, null, null, null, null, null, totalPage,
				Integer.valueOf(pageNo), model);
	}

}
