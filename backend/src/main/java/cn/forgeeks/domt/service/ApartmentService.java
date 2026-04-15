package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.Apartment;
import cn.forgeeks.domt.mapper.ApartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ApartmentService {

    private final ApartmentMapper apartmentMapper;

    public ApartmentService(ApartmentMapper apartmentMapper) {
        this.apartmentMapper = apartmentMapper;
    }

    public PageResult<Apartment> findPage(int pageNo, int pageSize, String apartmentName, String sex) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = apartmentMapper.count(apartmentName, sex);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<Apartment> results = apartmentMapper.findPage(offset, pageSize, apartmentName, sex);

        PageResult<Apartment> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<Apartment> findAll() {
        return apartmentMapper.findAll();
    }

    public List<Apartment> findBySex(String sex) {
        return apartmentMapper.findBySex(sex);
    }

    public Apartment getById(String apartmentId) {
        return apartmentMapper.getById(apartmentId);
    }

    @Transactional
    public void create(Apartment apartment) {
        apartment.setApartmentId(UUID.randomUUID().toString().replace("-", ""));
        apartmentMapper.insert(apartment);
    }

    @Transactional
    public void update(Apartment apartment) {
        apartmentMapper.update(apartment);
    }

    @Transactional
    public void deleteById(String apartmentId) {
        apartmentMapper.deleteById(apartmentId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        apartmentMapper.deleteBatch(ids);
    }
}
