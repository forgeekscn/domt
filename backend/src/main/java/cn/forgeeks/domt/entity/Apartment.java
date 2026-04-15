package cn.forgeeks.domt.entity;

import lombok.Data;

@Data
public class Apartment {
    private String apartmentId;
    private String apartmentName;
    private String sex;
    private String managerId;
    private Integer totalFloor;
    private Integer totalPeople;
}
