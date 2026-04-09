package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.PageResult;
import cn.forgeeks.domt.entity.Bedroom;
import cn.forgeeks.domt.mapper.BedroomMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BedroomService {

    private final BedroomMapper bedroomMapper;

    public BedroomService(BedroomMapper bedroomMapper) {
        this.bedroomMapper = bedroomMapper;
    }

    public PageResult<Bedroom> findPage(int pageNo, int pageSize, String bedroomName,
                                        String apartmentId, String status) {
        int offset = (pageNo - 1) * pageSize;
        long totalRecord = bedroomMapper.count(bedroomName, apartmentId, status);
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        List<Bedroom> results = bedroomMapper.findPage(offset, pageSize, bedroomName, apartmentId, status);

        PageResult<Bedroom> page = new PageResult<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setResults(results);
        return page;
    }

    public List<Bedroom> findAll() {
        return bedroomMapper.findAll();
    }

    public List<Bedroom> findByApartmentId(String apartmentId) {
        return bedroomMapper.findByApartmentId(apartmentId);
    }

    public List<Bedroom> findAvailableByApartmentId(String apartmentId) {
        return bedroomMapper.findAvailableByApartmentId(apartmentId);
    }

    public Bedroom getById(String bedroomId) {
        return bedroomMapper.getById(bedroomId);
    }

    public int countByApartmentId(String apartmentId) {
        return bedroomMapper.countByApartmentId(apartmentId);
    }

    public int countAvailableByApartmentId(String apartmentId) {
        return bedroomMapper.countAvailableByApartmentId(apartmentId);
    }

    @Transactional
    public void create(Bedroom bedroom) {
        bedroom.setBedroomId(UUID.randomUUID().toString().replace("-", ""));
        bedroomMapper.insert(bedroom);
    }

    @Transactional
    public void update(Bedroom bedroom) {
        bedroomMapper.update(bedroom);
    }

    @Transactional
    public void deleteById(String bedroomId) {
        bedroomMapper.deleteById(bedroomId);
    }

    @Transactional
    public void deleteBatch(List<String> ids) {
        bedroomMapper.deleteBatch(ids);
    }
}
