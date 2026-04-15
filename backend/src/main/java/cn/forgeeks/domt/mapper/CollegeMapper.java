package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollegeMapper {

    List<College> findAll();

    List<College> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                           @Param("collegeName") String collegeName);

    int count(@Param("collegeName") String collegeName);

    College getById(@Param("collegeId") String collegeId);

    int insert(College college);

    int update(College college);

    int deleteById(@Param("collegeId") String collegeId);

    int deleteBatch(@Param("ids") List<String> ids);
}
