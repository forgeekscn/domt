package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Apartment;
import cn.forgeeks.domt.service.ApartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public Result<PageResult<Apartment>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String apartmentName,
            @RequestParam(required = false) String sex) {
        PageResult<Apartment> page = apartmentService.findPage(pageNo, pageSize, apartmentName, sex);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Apartment> getById(@PathVariable String id) {
        return Result.success(apartmentService.getById(id));
    }

    @GetMapping("/all")
    public Result<List<Apartment>> getAll() {
        return Result.success(apartmentService.findAll());
    }

    @GetMapping("/by-sex/{sex}")
    public Result<List<Apartment>> getBySex(@PathVariable String sex) {
        return Result.success(apartmentService.findBySex(sex));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Apartment apartment) {
        apartmentService.create(apartment);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody Apartment apartment) {
        apartment.setApartmentId(id);
        apartmentService.update(apartment);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        apartmentService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        apartmentService.deleteBatch(ids);
        return Result.success();
    }
}
