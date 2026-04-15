package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.College;
import cn.forgeeks.domt.mapper.CollegeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CollegeService {

    private final CollegeMapper collegeMapper;

    public CollegeService(CollegeMapper collegeMapper) {
        this.collegeMapper = collegeMapper;
    }

    public PageResult<College> findPage(int pageNo, int pageSize, String collegeName) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = collegeMapper.count(collegeName);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<College> results = collegeMapper.findPage(offset, pageSize, collegeName);

        PageResult<College> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<College> findAll() {
        return collegeMapper.findAll();
    }

    public College getById(String collegeId) {
        return collegeMapper.getById(collegeId);
    }

    @Transactional
    public void create(College college) {
        college.setCollegeId(UUID.randomUUID().toString().replace("-", ""));
        collegeMapper.insert(college);
    }

    @Transactional
    public void update(College college) {
        collegeMapper.update(college);
    }

    @Transactional
    public void deleteById(String collegeId) {
        collegeMapper.deleteById(collegeId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        collegeMapper.deleteBatch(ids);
    }
}
