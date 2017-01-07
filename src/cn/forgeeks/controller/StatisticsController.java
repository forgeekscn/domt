package cn.forgeeks.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
import cn.forgeeks.util.DownloadUtil;
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
 
	@RequestMapping("/statis/todisbystu.action")
	public String todisbystu(Model model ){
		List<College> collegeList= collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/disbystu.jsp"; 
	}
	@RequestMapping("/statis/disbystu.action")
	public String disbystu(Model model,String stuId,String bedroomId){
		if(stuId==null||bedroomId==null||stuId.length()<1||bedroomId.length()<1){ 
			model.addAttribute("info","请选择待分配的学生和合适的宿舍"); return "/statis/sInfo2.jsp";  
		} 
		Student student= studentService.get(stuId);
		Bedroom oldBedroom= bedroomService.get(student.getBedroomId());
		Bedroom bedroom= bedroomService.get(bedroomId);
		
		student.setBedroomId(bedroomId);
		Integer oldNum=-1;
		Integer num=-1;
		if(oldBedroom!=null)   oldNum= Integer.valueOf(oldBedroom.getTotalBed().split("/")[0]);
		if(bedroom!=null)   num= Integer.valueOf(bedroom.getTotalBed().split("/")[0]);
		
		if(oldNum!=-1 && oldNum-1 >= 0 ) {oldBedroom.setStatus("N");oldBedroom.setTotalBed( (oldNum-1)+"/" + oldBedroom.getTotalBed().split("/")[1] );}
		if(num!=-1 && num+1 <= Integer.valueOf(bedroom.getTotalBed().split("/")[1])) {
			student.setBedroomId(bedroomId);
			student.setBedroomName(bedroom.getBedroomName());
			bedroom.setTotalBed( (num+1)+"/"+bedroom.getTotalBed().split("/")[1]); 
			if((num+1)==Integer.valueOf(bedroom.getTotalBed().split("/")[1]) ) bedroom.setStatus("Y");
			else  bedroom.setStatus("N");}
		else { model.addAttribute("info","该宿舍已经满员，请另选"); return "/statis/sInfo2.jsp";  } 
		studentService.update(student);
		bedroomService.update(oldBedroom);
		bedroomService.update(bedroom);
		model.addAttribute("info",student.getStudentName()+" 分配到了宿舍："+bedroom.getBedroomName());
		return "/statis/sInfo2.jsp";
	}
	@RequestMapping("/statis/toempbystu.action")
	public String empbystu(Model model){
		List<College> collegeList= collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/empbystu.jsp"; 
	}
	@RequestMapping("/statis/empbystu.action")
	public String empbystu(Model model, String stuId){
		if(stuId==null || stuId.length()<1) { 
			model.addAttribute("info","请选择待移出公寓的学生"); return "/statis/sInfo2.jsp";  
		}
		Student student = studentService.get(stuId);
		if(student.getBedroomId()==null) {
			model.addAttribute("info","该学生尚未分配宿舍，请返回查看！");
			return "/statis/sInfo2.jsp";
		}
		Bedroom bedroom= bedroomService.get(student.getBedroomId());
		student.setBedroomId(null);
		student.setBedroomName(null);
		bedroom.setStatus("N");
		bedroom.setTotalBed( (Integer.valueOf(bedroom.getTotalBed().split("/")[0])-1)+"/" + bedroom.getTotalBed().split("/")[1] ) ;
		studentService.update(student);
		bedroomService.update(bedroom);
		model.addAttribute("info",student.getStudentName()+" 被成功移出宿舍："+bedroom.getBedroomName());
		return "/statis/sInfo2.jsp";
	}
	
	
	
	@RequestMapping("/toprintstu.action")
	public String  toprintstu(Model model){
		model.addAttribute("info","数量庞大，请下载excel文件查看");
		model.addAttribute("code","printstu");
		return "/statis/sInfo2.jsp";
	}
	@RequestMapping("/printstu.action")
	public String  printstu( HttpServletResponse response) throws IOException{
		List<Student> list= studentService.find(null);
		String[] strs={"", "姓名", "宿舍", "是否分配宿舍(Y/N)", "性别","学号", "班级", "学院", "年级" };
		toExcel(response, "所有学生信息", strs, list);
		return "";
	}
	
	@RequestMapping("/toprintbr.action")
	public String  toprintbr(Model model){
		model.addAttribute("info","数量庞大，请下载excel文件查看");
		model.addAttribute("code","printbr");
		return "/statis/sInfo2.jsp";
	}
	@RequestMapping("/printbr.action")
	public String  printbr( HttpServletResponse response) throws IOException{
		List<Bedroom> list= bedroomService.find(null);
		String[] strs={"", "宿舍名称", "入住情况", "所在公寓","宿舍分配状态" };
		toBrExcel(response, "所有宿舍信息", strs, list);
		return "";
	}
	
	
	@RequestMapping("/print.action")
	public String print( HttpServletResponse response, String code,String arg,Model model) throws IOException{
		Map<String,String> map= new HashMap<String,String>();
		
		if(code.equals("disbycla")){
			map.put("classId", arg);
		}else if(code.equals("disbycol")){
			map.put("collegeId", arg);
		}else if(code.equals("statisbycla")){
			map.put("classId", arg);
		}else if(code.equals("statisbycol")){
			map.put("collegeId", arg);
		}
		List<Student> studentlist= studentService.find(map);
		String[] strs={"", "姓名", "宿舍", "是否分配宿舍(Y/N)", "性别","学号", "班级", "学院", "年级" };
		toExcel(response, "宿舍分配情况表",strs,	studentlist);
		return "";
	}
	
	
	
	public void toBrExcel( HttpServletResponse response, String tableName,String[] tableHeads,
			List<Bedroom> dataList) throws IOException{
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));
		int rowIndex = 1;
		int cellIndex = 1;
		Row row = sheet.createRow(1);
		row.setHeightInPoints(40);
		Cell cell = row.createCell(1);
		cell.setCellValue(tableName);
		cell.setCellStyle(this.setTableNameStyle(wb));
		rowIndex++;
		String[] heads =tableHeads;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(36);
		// row.setRowStyle(this.setTitleStyle(wb));
		for (int i = 1; i <= 4; i++) {
			cellIndex = i;
			cell = row.createCell(cellIndex);
//			cell.setCellStyle(this.setTitleStyle(wb));
			cell.setCellValue(heads[i]);
		}
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow(++rowIndex);
//			row.setHeightInPoints(33);
			Bedroom obj = dataList.get(i);
			for (int j = 1; j <= 7; j++) {
				cellIndex = j;
				cell = row.createCell(cellIndex);
//				cell.setCellStyle(this.setTextStyle(wb));
				switch (j) {
				case 1:
					cell.setCellValue(obj.getBedroomName() != null ? obj
							.getBedroomName() + "" : "空");
					break;
				case 2:
					cell.setCellValue(obj.getTotalBed() != null ? obj.getTotalBed()
							+ "" : "空");
					break;
				case 3:
					cell.setCellValue( obj.getBedroomName()!= null ? obj.getBedroomName().substring(0,2)+"栋" : "空");
					break;
				case 4:
					cell.setCellValue(obj.getStatus() != null ? obj
							.getStatus() + "" : "空");
					break;
			}
			}			
		}
		
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, tableName+".xls");
		
//		 OutputStream os=new FileOutputStream(new File("./note/"+tableName+".xls"));
//		 wb.write(os);
//		 os.close();
		
		
	}
	
	public void toExcel( HttpServletResponse response, String tableName,String[] tableHeads,
			List<Student> dataList) throws IOException{
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
//		sheet.setColumnWidth(1, 256 * 15);
//		sheet.setColumnWidth(2, 256 * 15);
//		sheet.setColumnWidth(3, 256 * 15);
//		sheet.setColumnWidth(4, 256 * 15);
//		sheet.setColumnWidth(5, 256 * 15);
//		sheet.setColumnWidth(6, 256 * 25);
//		sheet.setColumnWidth(7, 256 * 25);
		// sheet.setColumnWidth(0, 80);
//		sheet.setAutoFilter(new CellRangeAddress(1, 1, 7, 7));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 7));
		int rowIndex = 1;
		int cellIndex = 1;
		Row row = sheet.createRow(1);
		row.setHeightInPoints(40);
		Cell cell = row.createCell(1);
		cell.setCellValue(tableName);
		cell.setCellStyle(this.setTableNameStyle(wb));
		rowIndex++;
		String[] heads =tableHeads;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(36);
		// row.setRowStyle(this.setTitleStyle(wb));
		for (int i = 1; i <= 7; i++) {
			cellIndex = i;
			cell = row.createCell(cellIndex);
//			cell.setCellStyle(this.setTitleStyle(wb));
			cell.setCellValue(heads[i]);
		}
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow(++rowIndex);
//			row.setHeightInPoints(33);
			Student obj = dataList.get(i);
			for (int j = 1; j <= 7; j++) {
				cellIndex = j;
				cell = row.createCell(cellIndex);
//				cell.setCellStyle(this.setTextStyle(wb));
				switch (j) {
				case 1:
					cell.setCellValue(obj.getStudentName() != null ? obj
							.getStudentName() + "" : "空");
					break;
				case 2:
					cell.setCellValue(obj.getBedroomName() != null ? obj.getBedroomName()
							+ "" : "空");
					break;
				case 3:
					cell.setCellValue(obj.getStatus() != null ? obj.getStatus()
							+ "" : "空");
					break;
				case 4:
					cell.setCellValue(obj.getSex() != null ? obj
							.getSex() + "" : "空");
					break;
				case 5:
					cell.setCellValue(obj.getStudentNo() != null ? obj
							.getStudentNo() + "" : "空");
					break;
				case 6:
					cell.setCellValue(obj.getClassName() != null ? obj
							.getClassName() + "" : "空");
					break;
				case 7:
					cell.setCellValue(obj.getCollegeName() != null ? obj.getCollegeName() + ""
							: "空");
					break;
				case 8:
					cell.setCellValue(obj.getGrade() != null ? obj.getGrade() + ""
							: "空");
					break;
				}
			}

		}

		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, tableName+".xls");

//		 OutputStream os=new FileOutputStream(new File("./note/"+tableName+".xls"));
//		 wb.write(os);
//		 os.close();

		
	}


	public CellStyle setTitleStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Microsoft JhengHei UI");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight((short) 10);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}

	public CellStyle setTableNameStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Microsoft JhengHei UI");
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight((short) 20);
		font.setColor((short) 30);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}

	public CellStyle setTextStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
//		style.setWrapText(true);
		Font font = wb.createFont();
		font.setFontName("Microsoft JhengHei UI");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight((short) 10);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}

	
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
	
	@RequestMapping("/statis/toempbycollege.action")
	public String toempbycollege(Model model){
		List<College> collegeList= collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/empbycollege.jsp";
	}
	
	@RequestMapping("/statis/toempbyclass.action")
	public String toempbyclass(Model model){
		List<College> collegeList= collegeService.find(null);
		model.addAttribute("collegeList",collegeList);
		return "/statis/empbyclass.jsp";
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
		if(UtilFuns.isEmpty(classId)) classId=null;
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
	
	
	

	
	
	
	@RequestMapping("/statis/empbycollege.action")
	public String empbycollege(String classId,Model model){
		String info="宿舍腾空情况:<br>";
		Map<String, String> map= new HashMap<String,String>();
		if(UtilFuns.isNotEmpty(classId)) {  map.put("collegeId", classId); } else classId=null;
		map.put("status", "Y");
		List<Student> studentList= studentService.find(map);
		if(studentList.size()<1){
			info+="该学院所有学生都没分配到宿舍<br/>";
			model.addAttribute("info",info);
			return "/statis/info.jsp";
		}

		for(Student student:studentList){
			info+="< "+student.getStudentName()+" "+student.getClassName()+" "+
				  student.getCollegeName()+"> 该学生被腾空出"+student.getBedroomName()+"宿舍<br/>";
			Bedroom bedroom=bedroomService.get(student.getBedroomId());
			bedroom.setStatus("N");
			bedroom.setTotalBed("0/5");
			bedroomService.update(bedroom);
			
			student.setStatus("N");
			student.setBedroomId("");
			student.setBedroomName("");
			studentService.update(student);
		}
		model.addAttribute("info",info);
		return "/statis/info.jsp";
	}
	
	@RequestMapping("/statis/empbyclass.action")
	public String empbyclass(String classId,Model model){
		String info="宿舍腾空情况:<br>";
		Map<String, String> map= new HashMap<String,String>();
		if(UtilFuns.isNotEmpty(classId)) {  map.put("classId", classId); } else classId=null;
		map.put("status", "Y");
		
		List<Student> studentList= studentService.find(map);
		if(studentList.size()<1){
			info+="该班级所有学生都没分配到宿舍<br/>";
			model.addAttribute("info",info);
			return "/statis/info.jsp";
		}

		for(Student student:studentList){
			info+="<"+student.getStudentName()+" "+student.getClassName()+" "+
				  student.getCollegeName()+"> 该学生被腾空出"+student.getBedroomName()+"宿舍<br/>";
			Bedroom bedroom=bedroomService.get(student.getBedroomId());
			bedroom.setStatus("N");
			bedroom.setTotalBed("0/5");
			bedroomService.update(bedroom);
			
			student.setStatus("N");
			student.setBedroomId("");
			student.setBedroomName("");
			studentService.update(student);
		}
		
		model.addAttribute("info",info);
		return "/statis/info.jsp";
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
		info+="男生：\n<br/>";
		Map paraMap1=new HashMap();
		paraMap1.put("status","N");
		paraMap1.put("apmId",apartmentId);
		List<Bedroom> bedroomList1= bedroomService.find(paraMap1);
		
		for(Student student:boyStudentList){
			studentIndex++;
			if(studentIndex%5==1) forBrIndex++;
			Bedroom br;
			if(forBrIndex==bedroomList1.size()-1  ) {
				info+="由于该公寓宿舍已分配满，该学院部分男同学未分配到宿舍 ，稍后请再次为他们分配宿舍!<br/>";
				break;
			}else if (bedroomList1.size()==0){
				info+="由于该公寓宿舍已分配满，该学院部分男同学未分配到宿舍 ，稍后请再次为他们分配宿舍!<br/>";
				break;
			}
			else{
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
