package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Bedroom;
import cn.forgeeks.domt.service.BedroomService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bedrooms")
public class BedroomController {

    private final BedroomService bedroomService;

    public BedroomController(BedroomService bedroomService) {
        this.bedroomService = bedroomService;
    }

    @GetMapping
    public Result<PageResult<Bedroom>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String bedroomName,
            @RequestParam(required = false) String apartmentId,
            @RequestParam(required = false) String status) {
        PageResult<Bedroom> page = bedroomService.findPage(pageNo, pageSize, bedroomName, apartmentId, status);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Bedroom> getById(@PathVariable String id) {
        return Result.success(bedroomService.getById(id));
    }

    @GetMapping("/by-apartment/{apartmentId}")
    public Result<List<Bedroom>> getByApartmentId(@PathVariable String apartmentId) {
        return Result.success(bedroomService.findByApartmentId(apartmentId));
    }

    @GetMapping("/available/{apartmentId}")
    public Result<List<Bedroom>> getAvailableByApartmentId(@PathVariable String apartmentId) {
        return Result.success(bedroomService.findAvailableByApartmentId(apartmentId));
    }

    @GetMapping("/stats/{apartmentId}")
    public Result<Map<String, Integer>> getStatsByApartmentId(@PathVariable String apartmentId) {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("total", bedroomService.countByApartmentId(apartmentId));
        stats.put("available", bedroomService.countAvailableByApartmentId(apartmentId));
        return Result.success(stats);
    }

    @PostMapping
    public Result<Void> create(@RequestBody Bedroom bedroom) {
        bedroomService.create(bedroom);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody Bedroom bedroom) {
        bedroom.setBedroomId(id);
        bedroomService.update(bedroom);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        bedroomService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        bedroomService.deleteBatch(ids);
        return Result.success();
    }
}
