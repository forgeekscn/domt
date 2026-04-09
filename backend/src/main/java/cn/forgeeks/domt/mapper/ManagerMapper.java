package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerMapper {

    List<Manager> findAll();

    List<Manager> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                           @Param("managerName") String managerName);

    int count(@Param("managerName") String managerName);

    Manager getById(@Param("managerId") String managerId);

    Manager findByNameAndPassword(@Param("managerName") String managerName,
                                  @Param("managerPassword") String managerPassword);

    int insert(Manager manager);

    int update(Manager manager);

    int deleteById(@Param("managerId") String managerId);

    int deleteBatch(@Param("ids") List<String> ids);
}
