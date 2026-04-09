package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Classes;
import cn.forgeeks.domt.service.ClassesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping
    public Result<PageResult<Classes>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String collegeId,
            @RequestParam(required = false) String grade) {
        PageResult<Classes> page = classesService.findPage(pageNo, pageSize, className, collegeId, grade);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Classes> getById(@PathVariable String id) {
        return Result.success(classesService.getById(id));
    }

    @GetMapping("/all")
    public Result<List<Classes>> getAll() {
        return Result.success(classesService.findAll());
    }

    @GetMapping("/by-college/{collegeId}")
    public Result<List<Classes>> getByCollegeId(@PathVariable String collegeId) {
        return Result.success(classesService.findByCollegeId(collegeId));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Classes classes) {
        classesService.create(classes);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody Classes classes) {
        classes.setClassId(id);
        classesService.update(classes);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        classesService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        classesService.deleteBatch(ids);
        return Result.success();
    }
}
