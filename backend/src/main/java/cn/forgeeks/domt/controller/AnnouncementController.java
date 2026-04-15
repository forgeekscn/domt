package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.entity.Announcement;
import cn.forgeeks.domt.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping
    public Result<PageResult<Announcement>> list(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String title) {
        PageResult<Announcement> page = announcementService.findPage(pageNo, pageSize, title);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Announcement> getById(@PathVariable String id) {
        return Result.success(announcementService.getById(id));
    }

    @PostMapping
    public Result<Void> create(@RequestBody Announcement announcement) {
        announcementService.create(announcement);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody Announcement announcement) {
        announcement.setAnnouncementId(id);
        announcementService.update(announcement);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable String id) {
        announcementService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        announcementService.deleteBatch(ids);
        return Result.success();
    }
}
