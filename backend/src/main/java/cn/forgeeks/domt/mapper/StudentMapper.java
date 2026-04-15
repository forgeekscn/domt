package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> findAll();

    List<Student> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                           @Param("studentName") String studentName, @Param("collegeId") String collegeId,
                           @Param("grade") String grade, @Param("sex") String sex,
                           @Param("status") String status, @Param("classId") String classId,
                           @Param("bedroomId") String bedroomId, @Param("keyword") String keyword);

    int count(@Param("studentName") String studentName, @Param("collegeId") String collegeId,
              @Param("grade") String grade, @Param("sex") String sex,
              @Param("status") String status, @Param("classId") String classId,
              @Param("bedroomId") String bedroomId, @Param("keyword") String keyword);

    Student getById(@Param("studentId") String studentId);

    Student findByNameAndPassword(@Param("studentName") String studentName,
                                  @Param("studentPassword") String studentPassword);

    List<Student> findByClassId(@Param("classId") String classId);

    List<Student> findByBedroomId(@Param("bedroomId") String bedroomId);

    List<Student> findByCollegeId(@Param("collegeId") String collegeId);

    int countByStatus(@Param("status") String status);

    int countBySex(@Param("sex") String sex);

    int countByGrade(@Param("grade") String grade);

    int insert(Student student);

    int update(Student student);

    int deleteById(@Param("studentId") String studentId);

    int deleteBatch(@Param("ids") List<String> ids);
}
