package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.Classes;
import cn.forgeeks.domt.mapper.ClassesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClassesService {

    private final ClassesMapper classesMapper;

    public ClassesService(ClassesMapper classesMapper) {
        this.classesMapper = classesMapper;
    }

    public PageResult<Classes> findPage(int pageNo, int pageSize, String className,
                                        String collegeId, String grade) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = classesMapper.count(className, collegeId, grade);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<Classes> results = classesMapper.findPage(offset, pageSize, className, collegeId, grade);

        PageResult<Classes> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<Classes> findAll() {
        return classesMapper.findAll();
    }

    public List<Classes> findByCollegeId(String collegeId) {
        return classesMapper.findByCollegeId(collegeId);
    }

    public Classes getById(String classId) {
        return classesMapper.getById(classId);
    }

    @Transactional
    public void create(Classes classes) {
        classes.setClassId(UUID.randomUUID().toString().replace("-", ""));
        classesMapper.insert(classes);
    }

    @Transactional
    public void update(Classes classes) {
        classesMapper.update(classes);
    }

    @Transactional
    public void deleteById(String classId) {
        classesMapper.deleteById(classId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        classesMapper.deleteBatch(ids);
    }
}
