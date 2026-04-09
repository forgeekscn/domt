package cn.forgeeks.domt.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private int pageNo;
    private int pageSize;
    private long totalRecord;
    private int totalPage;
    private List<T> results;
}
