package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.Student;
import cn.forgeeks.domt.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public PageResult<Student> findPage(int pageNo, int pageSize, String studentName,
                                        String collegeId, String grade, String sex,
                                        String status, String classId, String bedroomId,
                                        String keyword) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = studentMapper.count(studentName, collegeId, grade, sex,
                status, classId, bedroomId, keyword);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<Student> results = studentMapper.findPage(offset, pageSize, studentName, collegeId,
                grade, sex, status, classId, bedroomId, keyword);

        PageResult<Student> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    public List<Student> findByClassId(String classId) {
        return studentMapper.findByClassId(classId);
    }

    public List<Student> findByBedroomId(String bedroomId) {
        return studentMapper.findByBedroomId(bedroomId);
    }

    public List<Student> findByCollegeId(String collegeId) {
        return studentMapper.findByCollegeId(collegeId);
    }

    public Student getById(String studentId) {
        return studentMapper.getById(studentId);
    }

    public Student findByNameAndPassword(String name, String password) {
        return studentMapper.findByNameAndPassword(name, password);
    }

    @Transactional
    public void create(Student student) {
        student.setStudentId(UUID.randomUUID().toString().replace("-", ""));
        student.setStudentPassword("123456");
        if (student.getBedroomId() != null && !student.getBedroomId().isEmpty()) {
            student.setStatus("Y");
        } else {
            student.setStatus("N");
        }
        studentMapper.insert(student);
    }

    @Transactional
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Transactional
    public void deleteById(String studentId) {
        studentMapper.deleteById(studentId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        studentMapper.deleteBatch(ids);
    }

    public int countByStatus(String status) {
        return studentMapper.countByStatus(status);
    }

    public int countBySex(String sex) {
        return studentMapper.countBySex(sex);
    }

    public int countByGrade(String grade) {
        return studentMapper.countByGrade(grade);
    }
}
