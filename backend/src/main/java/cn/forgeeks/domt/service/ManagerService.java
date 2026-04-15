package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.Manager;
import cn.forgeeks.domt.mapper.ManagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ManagerService {

    private final ManagerMapper managerMapper;

    public ManagerService(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    public PageResult<Manager> findPage(int pageNo, int pageSize, String managerName) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = managerMapper.count(managerName);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<Manager> results = managerMapper.findPage(offset, pageSize, managerName);

        PageResult<Manager> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<Manager> findAll() {
        return managerMapper.findAll();
    }

    public Manager getById(String managerId) {
        return managerMapper.getById(managerId);
    }

    public Manager findByNameAndPassword(String name, String password) {
        return managerMapper.findByNameAndPassword(name, password);
    }

    @Transactional
    public void create(Manager manager) {
        manager.setManagerId(UUID.randomUUID().toString().replace("-", ""));
        managerMapper.insert(manager);
    }

    @Transactional
    public void update(Manager manager) {
        managerMapper.update(manager);
    }

    @Transactional
    public void deleteById(String managerId) {
        managerMapper.deleteById(managerId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        managerMapper.deleteBatch(ids);
    }
}
