package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.Visitor;
import cn.forgeeks.domt.mapper.VisitorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VisitorService {

    private final VisitorMapper visitorMapper;

    public VisitorService(VisitorMapper visitorMapper) {
        this.visitorMapper = visitorMapper;
    }

    public PageResult<Visitor> findPage(int pageNo, int pageSize, String visitorName, String bedroomName) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = visitorMapper.count(visitorName, bedroomName);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<Visitor> results = visitorMapper.findPage(offset, pageSize, visitorName, bedroomName);

        PageResult<Visitor> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<Visitor> findAll() {
        return visitorMapper.findAll();
    }

    public Visitor getById(String visitorId) {
        return visitorMapper.getById(visitorId);
    }

    @Transactional
    public void create(Visitor visitor) {
        visitor.setVisitorId(UUID.randomUUID().toString().replace("-", ""));
        visitorMapper.insert(visitor);
    }

    @Transactional
    public void update(Visitor visitor) {
        visitorMapper.update(visitor);
    }

    @Transactional
    public void deleteById(String visitorId) {
        visitorMapper.deleteById(visitorId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        visitorMapper.deleteBatch(ids);
    }
}
