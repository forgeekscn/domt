package cn.forgeeks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.forgeeks.domain.Apartment;
import cn.forgeeks.domain.Bedroom;
import cn.forgeeks.domain.Classes;
import cn.forgeeks.domain.College;
import cn.forgeeks.domain.Student;
import cn.forgeeks.service.AnnouncementService;
import cn.forgeeks.service.ApartmentService;
import cn.forgeeks.service.BedroomService;
import cn.forgeeks.service.ClassesService;
import cn.forgeeks.service.CollegeService;
import cn.forgeeks.service.StudentService;
import cn.forgeeks.service.VisitorService;
import cn.forgeeks.util.UtilFuns;

@Controller
public class StatisticsController {
	@Resource
	StudentService studentService;
	@Resource
	CollegeService collegeService;
	@Resource
	ClassesService classesService;
	@Resource
	VisitorService visitorService;
	@Resource
	AnnouncementService announcementService;
	@Resource 
	ApartmentService apartmentService;
	@Resource 
	BedroomService bedroomService;
	
	
	
	@RequestMapping("/statis/toviewdisbycollege.action")
	public String toviewdisbycollege(Model model){
		List<College> collegeList=collegeService.find(null);
		model.addAttribute("collegeList" , collegeList);
		return "/statis/viewdisbycollege.jsp";
	}
	
	@RequestMapping("/statis/viewdisbycollege.action")
	public String viewdisbycollege(String classId,Model model){
		Map<String,String> map= new HashMap<String,String>();
		map.put("collegeId", classId);
		List<Student> stuList= studentService.find(map);
		String info="";
		int numB=0,disB=0;
		int numG=0,disG=0;
		
		for(Student student:stuList){
			if(student.getSex().equals("男")) numB++;else numG++; 
			if(student.getStatus().equals("Y")) {
				if(student.getSex().equals("男")) disB++;else disG++;
			}else {
			}
		}
		info+="该学院共有"+(numB+numG)+"人，其中男生"+numB+"人，女生"+numG+"人 <br/>";
		info+="其中已经分配到宿舍的有"+(disB+disG)+"人，其中男生"+disB+"人，女生"+disG+"人 <br/>";
		info+="其中没有分配宿舍的有"+(numB+numG-disB-disG)+"人，其中男生"+(numB-disB)+"人，女生"+(numG-disG)+"人 <br/>";
		
		model.addAttribute("infoList",stuList);
		model.addAttribute("info",info);
		return "/statis/sInfo1.jsp";
	}
	
	
	
	
	@RequestMapping("/statis/toviewdisbycla.action")
	public String toviewdisbycla(Model model){
		List<College> collegeList=collegeService.find(null);
		model.addAttribute("collegeList" , collegeList);
		return "/statis/viewdisbycla.jsp";
	}
	
	@RequestMapping("/statis/viewdisbycla.action")
	public String viewdisbycla(String classId,Model model){
		Map<String,String> map= new HashMap<String,String>();
		map.put("classId", classId);
		List<Student> stuList= studentService.find(map);
		String info="";
		int numB=0,disB=0;
		int numG=0,disG=0;
		
		for(Student student:stuList){
			if(student.getSex().equals("男")) numB++;else numG++; 
			if(student.getStatus().equals("Y")) {
				if(student.getSex().equals("男")) disB++;else disG++;
			}else {
			}
		}
		info+="该班级共有"+(numB+numG)+"人，其中男生"+numB+"人，女生"+numG+"人 <br/>";
		info+="其中已经分配到宿舍的有"+(disB+disG)+"人，其中男生"+disB+"人，女生"+disG+"人 <br/>";
		info+="其中没有分配宿舍的有"+(numB+numG-disB-disG)+"人，其中男生"+(numB-disB)+"人，女生"+(numG-disG)+"人 <br/>";
		
		model.addAttribute("infoList",stuList);
		model.addAttribute("info",info);
		return "/statis/sInfo1.jsp";
	}
	
	
	
	@RequestMapping("/statis/tostatisstubycollege.action")
	public String toStatisstubycollege(Model model){
		List<College> collegeList= collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/statisstubycollege.jsp";
	}
	
	@RequestMapping("/statis/statisstubycollege.action")
	public String statisstubycollege(String classId,Model model){
		String info="";
		Map<String,String> map= new HashMap<String,String>();
		map.put("collegeId",classId);
		List<Student> stuList= studentService.find(map);
		
		int numBoy=0;
		int numGirl=0;
				
		for(Student student:stuList){
			if(student.getSex().equals("男")) numBoy++;else numGirl++;
		}
		
		info+="该学院共有"+(numBoy+numGirl)+"人，其中男生"+numBoy+"人，女生"+numGirl+"人<br/>";
		
		model.addAttribute("info",info);
		model.addAttribute("infoList",stuList);
		return "/statis/sInfo.jsp";
	}
	
	
	
	@RequestMapping("/statis/tostatisstubycla.action")
	public String toStatisstubycla(Model model){
		List<College> collegeList= collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/statisstubycla.jsp";
	}

	@RequestMapping("/statis/statisstubycla.action")
	public String statisstubycla(String classId,Model model){
		String info="";

		Map<String,String> map= new HashMap<String,String>();
		map.put("classId",classId);
		List<Student> stuList= studentService.find(map);
		
		int numBoy=0;
		int numGirl=0;
				
		for(Student student:stuList){
			if(student.getSex().equals("男")) numBoy++;else numGirl++;
		}
		
		info+="该班级共有"+(numBoy+numGirl)+"人，其中男生"+numBoy+"人，女生"+numGirl+"人<br/>";
		
		model.addAttribute("info",info);
		model.addAttribute("infoList",stuList);
		return "/statis/sInfo.jsp";
	}
	
	
	
	
	
	@RequestMapping("/statis/disbycollege.action")
	public String distributebycollege(String classId,String sex1,String apartmentId,
			String sex2,String apartmentId2,Model model){
		Map<String, String> map= new HashMap<String,String>();
		
		if(UtilFuns.isNotEmpty(classId)) {  map.put("classId", classId); } else classId=null;
		if(UtilFuns.isNotEmpty(sex1)) {  
			if(sex1.equals("b")) map.put("sex1", "男");
			else if(sex1.equals("g")) map.put("sex1", "女");
		} else sex1=null;
		if(UtilFuns.isNotEmpty(sex2)) {  
			if(sex2.equals("b")) map.put("sex2", "男");
			else if(sex2.equals("g")) map.put("sex2", "女");
		} else sex2=null;
		
		if(UtilFuns.isNotEmpty(apartmentId)) {  map.put("apartmentId", apartmentId); } else apartmentId=null;
		if(UtilFuns.isNotEmpty(apartmentId2)) {  map.put("apartmentId2", apartmentId2); } else apartmentId2=null;
		
		String info="<br/>宿舍分配情况<br/><br/>";
		int forBrIndex=-1;
		int studentIndex=0;
		
		if(sex1!=null && apartmentId!=null){}
		if(sex2!=null && apartmentId2!=null){}
		
		Map paraMap=new HashMap();
		paraMap.put("collegeId",classId);
		paraMap.put("status","N");
		paraMap.put("sex","男");
		List<Student> boyStudentList=studentService.find(paraMap);
		int studentNum=boyStudentList.size(); 
		int forBrNum=( (studentNum*1.0/5-studentNum/5)<0.0001?studentNum/5:studentNum/5+1);
		System.out.println("该学院未分配宿舍的男生人数:"+studentNum);
		System.out.println("至少需要宿舍间数:"+forBrNum);
		
		info+="男生：\n<br/>";
		
		Map paraMap1=new HashMap();
		paraMap1.put("status","N");
		paraMap1.put("apmId",apartmentId);
		List<Bedroom> bedroomList1= bedroomService.find(paraMap1);
		
		for(Student student:boyStudentList){
			studentIndex++;
			if(studentIndex%5==1) forBrIndex++;
			Bedroom br;
			if(forBrIndex==bedroomList1.size()-1 || bedroomList1.size()==0 ) {
				info+="由于该公寓宿舍已分配满，该学院部分男同学未分配到宿舍 ，稍后请再次为他们分配宿舍!<br/>";
//				apartmentService.get(apartmentId).set
				break;
			}else{
				br=bedroomList1.get(forBrIndex);
			}
			student.setBedroomId(br.getBedroomId());
			student.setBedroomName(br.getBedroomName());
			student.setStatus("Y");
			studentService.update(student);
			br.setStatus("Y");
			br.setTotalBed( (studentIndex%5==0?5:studentIndex%5)+"/5");
			bedroomService.update(br);
			info+=student.getClassName()+" : "+student.getStudentName()+"   分配的宿舍    "+br.getBedroomName()+"<br/>";
		}
		
		
		
		info+="<br/> 女生：<br/>";
		
		forBrIndex=-1;
		studentIndex=0;
		
		Map paraMap11=new HashMap();
		paraMap11.put("collegeId",classId);
		paraMap11.put("status","N");
		paraMap11.put("sex","女");
		List<Student> girlStudentList=studentService.find(paraMap11);
		int studentNum2=girlStudentList.size(); 
		int forBrNum2=( (studentNum2*1.0/5-studentNum2/5)<0.0001?studentNum2/5:studentNum2/5+1);
		System.out.println("该学院未分配宿舍的女生人数:"+studentNum2);
		System.out.println("至少需要宿舍间数:"+forBrNum2);
		
		Map paraMap2=new HashMap();
		paraMap2.put("status","N");
		paraMap2.put("apmId",apartmentId2);
		List<Bedroom> bedroomList2= bedroomService.find(paraMap2);
		
		
		for(Student student:girlStudentList){
			studentIndex++;
			if(studentIndex%5==1) forBrIndex++;
			Bedroom br;
			if(forBrIndex==bedroomList2.size()-1  || bedroomList2.size()==0  ) {
				info+="由于该公寓宿舍已分配满，该学院部分男同学未分配到宿舍 ，稍后请再次为他们分配宿舍!<br/>";
				break;
			}else{
				br=bedroomList2.get(forBrIndex);
			}
			student.setBedroomId(br.getBedroomId());
			student.setBedroomName(br.getBedroomName());
			student.setStatus("Y");
			studentService.update(student);
			br.setStatus("Y");
			br.setTotalBed((studentIndex%5==0?5:studentIndex%5)+"/5");
			bedroomService.update(br);
			info+=student.getClassName()+" : "+student.getStudentName()+"   分配的宿舍     "+br.getBedroomName()+"<br/>";
		}
		
		info+="<br/>以上同学成功分配到宿舍<br/><br/>";
		System.out.println(info);		
		model.addAttribute("info",info);
		
		return "/statis/info.jsp";
	}
	
	@RequestMapping("/statis/disbycla.action")
	public String distributebyclass(String classId,String sex1,String apartmentId,
			String sex2,String apartmentId2,Model model){
		Map<String, String> map= new HashMap<String,String>();
		
		if(UtilFuns.isNotEmpty(classId)) {  map.put("classId", classId); } else classId=null;
		if(UtilFuns.isNotEmpty(sex1)) {  
			if(sex1.equals("b")) map.put("sex1", "男");
			else if(sex1.equals("g")) map.put("sex1", "女");
		} else sex1=null;
		if(UtilFuns.isNotEmpty(sex2)) {  
			if(sex2.equals("b")) map.put("sex2", "男");
			else if(sex2.equals("g")) map.put("sex2", "女");
		} else sex2=null;
		
		if(UtilFuns.isNotEmpty(apartmentId)) {  map.put("apartmentId", apartmentId); } else apartmentId=null;
		if(UtilFuns.isNotEmpty(apartmentId2)) {  map.put("apartmentId2", apartmentId2); } else apartmentId2=null;

		String info="<br/>宿舍分配情况<br/><br/>";
		int forBrIndex=-1;
		int studentIndex=0;
		
		if(sex1!=null && apartmentId!=null){}
		if(sex2!=null && apartmentId2!=null){}
		
		Map paraMap=new HashMap();
		paraMap.put("classId",classId);
		paraMap.put("status","N");
		paraMap.put("sex","男");
		List<Student> boyStudentList=studentService.find(paraMap);
		int studentNum=boyStudentList.size(); 
		int forBrNum=( (studentNum*1.0/5-studentNum/5)<0.0001?studentNum/5:studentNum/5+1);
		System.out.println("该班级未分配宿舍的男生人数:"+studentNum);
		System.out.println("至少需要宿舍间数:"+forBrNum);

		info+="男生：\n<br/>";
		
		Map paraMap1=new HashMap();
		paraMap1.put("status","N");
		paraMap1.put("apmId",apartmentId);
		List<Bedroom> bedroomList1= bedroomService.find(paraMap1);
		
		for(Student student:boyStudentList){
			studentIndex++;
			if(studentIndex%5==1) forBrIndex++;
			Bedroom br;
			if(forBrIndex==studentNum-1) {
				info+="由于该公寓宿舍已分配满，该班级部分男同学未分配到宿舍 ，稍后请再次为他们分配宿舍!<br/>";
//				apartmentService.get(apartmentId).set
				break;
			}else{
				br=bedroomList1.get(forBrIndex);
			}
			student.setBedroomId(br.getBedroomId());
			student.setBedroomName(br.getBedroomName());
			student.setStatus("Y");
			studentService.update(student);
			br.setStatus("Y");
			br.setTotalBed( (studentIndex%5==0?5:studentIndex%5)+"/5");
			bedroomService.update(br);
			info+=student.getStudentName()+" 分配的宿舍 "+br.getBedroomName()+"<br/>";
		}
		
		
		
		info+="<br/> 女生：<br/>";
		
		forBrIndex=-1;
		studentIndex=0;

		Map paraMap11=new HashMap();
		paraMap11.put("classId",classId);
		paraMap11.put("status","N");
		paraMap11.put("sex","女");
		List<Student> girlStudentList=studentService.find(paraMap11);
		int studentNum2=girlStudentList.size(); 
		int forBrNum2=( (studentNum2*1.0/5-studentNum2/5)<0.0001?studentNum2/5:studentNum2/5+1);
		System.out.println("该班级未分配宿舍的女生人数:"+studentNum2);
		System.out.println("至少需要宿舍间数:"+forBrNum2);

		Map paraMap2=new HashMap();
		paraMap2.put("status","N");
		paraMap2.put("apmId",apartmentId2);
		List<Bedroom> bedroomList2= bedroomService.find(paraMap2);
		

		for(Student student:girlStudentList){
			studentIndex++;
			if(studentIndex%5==1) forBrIndex++;
			Bedroom br;
			if(forBrIndex==studentNum2-1) {
				info+="由于该公寓宿舍已分配满，该学院部分女同学未分配到宿舍 ，稍后请再次为他们分配宿舍!<br/>";
				break;
			}else{
				br=bedroomList2.get(forBrIndex);
			}
			student.setBedroomId(br.getBedroomId());
			student.setBedroomName(br.getBedroomName());
			student.setStatus("Y");
			studentService.update(student);
			br.setStatus("Y");
			br.setTotalBed((studentIndex%5==0?5:studentIndex%5)+"/5");
			bedroomService.update(br);
			info+=student.getStudentName()+" 分配的宿舍 "+br.getBedroomName()+"<br/>";
		}
		
		info+="<br/>以上同学成功分配到宿舍<br/><br/>";
		System.out.println(info);		
		model.addAttribute("info",info);
		
		return "/statis/info.jsp";
	}
	
	
	@RequestMapping("/statis/pushclassdata.action")
	public void pushclassdata(){
		List<College> collegelist= collegeService.find(null);
		
		for(College college:collegelist){
			for(int i=0;i<33;i++){
				Classes cla= new Classes();
				cla.setClassId(UUID.randomUUID().toString().substring(0,8));
				cla.setClassName("1"+(int)(Math.random()*100000)+"班");
				cla.setCoach("刘辅导员");
				cla.setCoachCall("1379880086");
				if(i<3)	cla.setGrade("大一");
				else if(i<13) cla.setGrade("大二");
				else if(i<20) cla.setGrade("大三");
				else if(i<33) cla.setGrade("大四");
				cla.setCollegeId(college.getCollegeId());
				classesService.insert(cla);
			}
		}
		
		System.out.println("");
		
	}
	
	@RequestMapping("/statis/pushstudentdata.action")
	public void pushstudentdata(){
		List<Student> li= studentService.find(null);
		for(Student s: li){
			studentService.deleteById(s.getStudentId());
		}
		
		List<Classes> classeslist= classesService.find(null);
		int x=0;
		for(Classes c:classeslist){
			for(int i=0;i<35;i++){
				if(i<10){
					Student student= new Student();
					student.setStudentId(UUID.randomUUID().toString().substring(0,8));
					student.setStudentName("张美丽"+(int)(Math.random()*1000));
					student.setClassId(c.getClassId());
					student.setClassName(c.getClassName());
					student.setCollegeId(c.getCollegeId());
					student.setCollegeName(collegeService.get(c.getCollegeId()).getCollegeName());
					student.setSex("女");
					
					student.setGrade(c.getGrade());
					
					student.setStudentNo("130"+(int)(Math.random()*1000000) );
					student.setStatus("N");
					student.setStudentPassword("123");
					studentService.insert(student);
					System.out.println(++x);
				}else {
					Student student= new Student();
					student.setStudentId(UUID.randomUUID().toString().substring(0,8));
					student.setStudentName("牛二"+UUID.randomUUID().toString().substring(0,3));
					student.setClassId(c.getClassId());
					student.setClassName(c.getClassName());
					student.setCollegeId(c.getCollegeId());
					student.setCollegeName(collegeService.get(c.getCollegeId()).getCollegeName());
					student.setSex("男");
					student.setGrade(c.getGrade());
					student.setStudentNo("130"+(int)(Math.random()*1000000) );
					student.setStatus("N");
					student.setStudentPassword("123");
					studentService.insert(student);
					System.out.println(++x);
				}
				
			}
		}
		
		System.out.println("");
		
	}

	@RequestMapping("/statis/pushapmdata.action")
	public void pushapmdata(){
		for(int i=1;i<=20;i++){
			Apartment a=new Apartment();
			
			a.setApartmentId(UUID.randomUUID().toString().substring(0,8));
			a.setApartmentName(i+"A");
			if(i<=5) a.setSex("女");else a.setSex("男");
			a.setTotalFloor("7");
			a.setTotalPeople("0");
			apartmentService.insert(a);
			
			a.setApartmentId(UUID.randomUUID().toString().substring(0,8));
			a.setApartmentName(i+"B");
			if(i<=5) a.setSex("女");else a.setSex("男");
			a.setTotalFloor("7");
			a.setTotalPeople("0");
			apartmentService.insert(a);
			
			
			a.setApartmentId(UUID.randomUUID().toString().substring(0,8));
			a.setApartmentName(i+"C");
			if(i<=5) a.setSex("女");else a.setSex("男");
			a.setTotalFloor("7");
			a.setTotalPeople("0");
			apartmentService.insert(a);
			
		}
		System.out.println();
	}
	
	@RequestMapping("/statis/pushbedroomdata.action")
	public void pushbedroomdata(){
		
		List<Bedroom> l= bedroomService.find(null);
		for(Bedroom b:l){
			bedroomService.deleteById(b.getBedroomId());
		}
			
		int k=0;
		List<Apartment> list=apartmentService.find(null);
		for(Apartment a:list){
			for( int i=1;i<=7;i++){
				for(int j=1;j<=30;j++){
					Bedroom b= new Bedroom();
					b.setBedroomId(UUID.randomUUID().toString().substring(0,9));
					b.setApartmentId(a.getApartmentId());
					
					b.setBedroomName(a.getApartmentName()+ i +""+( j<10?"0"+j:j ));
					b.setStatus("N");
					b.setTotalBed("0/5");
					bedroomService.insert(b);
					System.out.println(++k);
				}
			}
		}
	}
	
	@RequestMapping("/statis/todisbycla.action")
	public String todistributebyclass(Model model){
		List<College> collegeList=collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/disbycla.jsp";
	}
	@RequestMapping("/statis/todisbycollege.action")
	public String todistributebycollege(Model model){
		List<College> collegeList=collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/disbycollege.jsp";
	}
	
}
