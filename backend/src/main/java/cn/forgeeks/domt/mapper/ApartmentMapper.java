package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.Apartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApartmentMapper {

    List<Apartment> findAll();

    List<Apartment> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                             @Param("apartmentName") String apartmentName, @Param("sex") String sex);

    int count(@Param("apartmentName") String apartmentName, @Param("sex") String sex);

    Apartment getById(@Param("apartmentId") String apartmentId);

    List<Apartment> findBySex(@Param("sex") String sex);

    int insert(Apartment apartment);

    int update(Apartment apartment);

    int deleteById(@Param("apartmentId") String apartmentId);

    int deleteBatch(@Param("ids") List<String> ids);
}
