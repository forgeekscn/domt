package cn.forgeeks.domt.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String name;
    private String type; // "root", "manager", "student"
    private Object user;
}
