package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VisitorMapper {

    List<Visitor> findAll();

    List<Visitor> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                           @Param("visitorName") String visitorName, @Param("bedroomName") String bedroomName);

    int count(@Param("visitorName") String visitorName, @Param("bedroomName") String bedroomName);

    Visitor getById(@Param("visitorId") String visitorId);

    int insert(Visitor visitor);

    int update(Visitor visitor);

    int deleteById(@Param("visitorId") String visitorId);

    int deleteBatch(@Param("ids") List<String> ids);
}
