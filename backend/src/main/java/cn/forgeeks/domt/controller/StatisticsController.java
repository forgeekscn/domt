package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Bedroom;
import cn.forgeeks.domt.entity.Student;
import cn.forgeeks.domt.service.BedroomService;
import cn.forgeeks.domt.service.StatisticsService;
import cn.forgeeks.domt.service.StudentService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StudentService studentService;
    private final BedroomService bedroomService;

    public StatisticsController(StatisticsService statisticsService,
                                StudentService studentService,
                                BedroomService bedroomService) {
        this.statisticsService = statisticsService;
        this.studentService = studentService;
        this.bedroomService = bedroomService;
    }

    @PostMapping("/allocate")
    public Result<Void> allocate(@RequestBody Map<String, String> body) {
        statisticsService.allocateStudent(body.get("studentId"), body.get("bedroomId"));
        return Result.success();
    }

    @PostMapping("/deallocate")
    public Result<Void> deallocate(@RequestBody Map<String, String> body) {
        statisticsService.deallocateStudent(body.get("studentId"));
        return Result.success();
    }

    @GetMapping("/summary")
    public Result<Map<String, Object>> summary() {
        return Result.success(statisticsService.getStatistics());
    }

    @GetMapping("/export/students")
    public void exportStudents(HttpServletResponse response) throws IOException {
        List<Student> students = studentService.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");

        Row header = sheet.createRow(0);
        String[] headers = {"学号", "姓名", "性别", "班级", "学院", "年级", "宿舍", "分配状态"};
        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(s.getStudentNo());
            row.createCell(1).setCellValue(s.getStudentName());
            row.createCell(2).setCellValue(s.getSex());
            row.createCell(3).setCellValue(s.getClassName());
            row.createCell(4).setCellValue(s.getCollegeName());
            row.createCell(5).setCellValue(s.getGrade());
            row.createCell(6).setCellValue(s.getBedroomName());
            row.createCell(7).setCellValue("Y".equals(s.getStatus()) ? "已分配" : "未分配");
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @GetMapping("/export/bedrooms")
    public void exportBedrooms(HttpServletResponse response) throws IOException {
        List<Bedroom> bedrooms = bedroomService.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("宿舍信息");

        Row header = sheet.createRow(0);
        String[] headers = {"宿舍编号", "宿舍名称", "所属公寓", "床位数", "状态"};
        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        for (int i = 0; i < bedrooms.size(); i++) {
            Bedroom b = bedrooms.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(b.getBedroomId());
            row.createCell(1).setCellValue(b.getBedroomName());
            row.createCell(2).setCellValue(b.getApartmentId());
            row.createCell(3).setCellValue(b.getTotalBed());
            row.createCell(4).setCellValue("Y".equals(b.getStatus()) ? "已满" : "未满");
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=bedrooms.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
