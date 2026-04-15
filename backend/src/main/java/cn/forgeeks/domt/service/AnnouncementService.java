package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.Announcement;
import cn.forgeeks.domt.mapper.AnnouncementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class AnnouncementService {

    private final AnnouncementMapper announcementMapper;

    public AnnouncementService(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    public PageResult<Announcement> findPage(int pageNo, int pageSize, String title) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = announcementMapper.count(title);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<Announcement> results = announcementMapper.findPage(offset, pageSize, title);

        PageResult<Announcement> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<Announcement> findAll() {
        return announcementMapper.findAll();
    }

    public Announcement getById(String announcementId) {
        return announcementMapper.getById(announcementId);
    }

    @Transactional
    public void create(Announcement announcement) {
        announcement.setAnnouncementId(UUID.randomUUID().toString().replace("-", ""));
        announcement.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        announcementMapper.insert(announcement);
    }

    @Transactional
    public void update(Announcement announcement) {
        announcementMapper.update(announcement);
    }

    @Transactional
    public void deleteById(String announcementId) {
        announcementMapper.deleteById(announcementId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        announcementMapper.deleteBatch(ids);
    }
}
