package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassesMapper {

    List<Classes> findAll();

    List<Classes> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                           @Param("className") String className, @Param("collegeId") String collegeId,
                           @Param("grade") String grade);

    int count(@Param("className") String className, @Param("collegeId") String collegeId,
              @Param("grade") String grade);

    Classes getById(@Param("classId") String classId);

    List<Classes> findByCollegeId(@Param("collegeId") String collegeId);

    int insert(Classes classes);

    int update(Classes classes);

    int deleteById(@Param("classId") String classId);

    int deleteBatch(@Param("ids") List<String> ids);
}
