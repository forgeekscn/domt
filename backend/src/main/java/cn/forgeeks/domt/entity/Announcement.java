package cn.forgeeks.domt.entity;

import lombok.Data;

@Data
public class Announcement {
    private String announcementId;
    private String title;
    private String content;
    private String createTime;
}
