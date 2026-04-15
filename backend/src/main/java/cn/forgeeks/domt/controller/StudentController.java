package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Student;
import cn.forgeeks.domt.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Result<PageResult<Student>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String collegeId,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String classId,
            @RequestParam(required = false) String bedroomId,
            @RequestParam(required = false) String keyword) {
        PageResult<Student> page = studentService.findPage(pageNo, pageSize, studentName,
                collegeId, grade, sex, status, classId, bedroomId, keyword);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable String id) {
        return Result.success(studentService.getById(id));
    }

    @GetMapping("/by-class/{classId}")
    public Result<List<Student>> getByClassId(@PathVariable String classId) {
        return Result.success(studentService.findByClassId(classId));
    }

    @GetMapping("/by-bedroom/{bedroomId}")
    public Result<List<Student>> getByBedroomId(@PathVariable String bedroomId) {
        return Result.success(studentService.findByBedroomId(bedroomId));
    }

    @GetMapping("/by-college/{collegeId}")
    public Result<List<Student>> getByCollegeId(@PathVariable String collegeId) {
        return Result.success(studentService.findByCollegeId(collegeId));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Student student) {
        studentService.create(student);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody Student student) {
        student.setStudentId(id);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        studentService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        studentService.deleteBatch(ids);
        return Result.success();
    }
}
