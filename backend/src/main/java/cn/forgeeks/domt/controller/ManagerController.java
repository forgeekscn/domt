package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Manager;
import cn.forgeeks.domt.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public Result<PageResult<Manager>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String managerName) {
        PageResult<Manager> page = managerService.findPage(pageNo, pageSize, managerName);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Manager> getById(@PathVariable String id) {
        return Result.success(managerService.getById(id));
    }

    @GetMapping("/all")
    public Result<List<Manager>> getAll() {
        return Result.success(managerService.findAll());
    }

    @PostMapping
    public Result<Void> create(@RequestBody Manager manager) {
        managerService.create(manager);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody Manager manager) {
        manager.setManagerId(id);
        managerService.update(manager);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        managerService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        managerService.deleteBatch(ids);
        return Result.success();
    }
}
