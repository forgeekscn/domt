package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.Bedroom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BedroomMapper {

    List<Bedroom> findAll();

    List<Bedroom> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                           @Param("bedroomName") String bedroomName, @Param("apartmentId") String apartmentId,
                           @Param("status") String status);

    int count(@Param("bedroomName") String bedroomName, @Param("apartmentId") String apartmentId,
              @Param("status") String status);

    Bedroom getById(@Param("bedroomId") String bedroomId);

    List<Bedroom> findByApartmentId(@Param("apartmentId") String apartmentId);

    List<Bedroom> findAvailableByApartmentId(@Param("apartmentId") String apartmentId);

    int countByApartmentId(@Param("apartmentId") String apartmentId);

    int countAvailableByApartmentId(@Param("apartmentId") String apartmentId);

    int insert(Bedroom bedroom);

    int update(Bedroom bedroom);

    int deleteById(@Param("bedroomId") String bedroomId);

    int deleteBatch(@Param("ids") List<String> ids);
}
