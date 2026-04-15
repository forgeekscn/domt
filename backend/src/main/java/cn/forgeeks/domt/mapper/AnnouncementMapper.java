package cn.forgeeks.domt.mapper;

import cn.forgeeks.domt.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    List<Announcement> findAll();

    List<Announcement> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                                @Param("title") String title);

    int count(@Param("title") String title);

    Announcement getById(@Param("announcementId") String announcementId);

    int insert(Announcement announcement);

    int update(Announcement announcement);

    int deleteById(@Param("announcementId") String announcementId);

    int deleteBatch(@Param("ids") List<String> ids);
}
