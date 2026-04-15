package cn.forgeeks.domt.entity;

import lombok.Data;

@Data
public class Bedroom {
    private String bedroomId;
    private String apartmentId;
    private String bedroomName;
    private String status;
    private String totalBed;
}
