package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Visitor;
import cn.forgeeks.domt.service.VisitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public Result<PageResult<Visitor>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String visitorName,
            @RequestParam(required = false) String bedroomName) {
        PageResult<Visitor> page = visitorService.findPage(pageNo, pageSize, visitorName, bedroomName);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Visitor> getById(@PathVariable String id) {
        return Result.success(visitorService.getById(id));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Visitor visitor) {
        visitorService.create(visitor);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody Visitor visitor) {
        visitor.setVisitorId(id);
        visitorService.update(visitor);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        visitorService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        visitorService.deleteBatch(ids);
        return Result.success();
    }
}
