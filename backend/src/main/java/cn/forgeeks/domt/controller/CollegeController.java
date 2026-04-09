package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.College;
import cn.forgeeks.domt.service.CollegeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

    private final CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping
    public Result<PageResult<College>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String collegeName) {
        PageResult<College> page = collegeService.findPage(pageNo, pageSize, collegeName);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<College> getById(@PathVariable String id) {
        return Result.success(collegeService.getById(id));
    }

    @GetMapping("/all")
    public Result<List<College>> getAll() {
        return Result.success(collegeService.findAll());
    }

    @PostMapping
    public Result<Void> create(@RequestBody College college) {
        collegeService.create(college);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody College college) {
        college.setCollegeId(id);
        collegeService.update(college);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        collegeService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        collegeService.deleteBatch(ids);
        return Result.success();
    }
}
