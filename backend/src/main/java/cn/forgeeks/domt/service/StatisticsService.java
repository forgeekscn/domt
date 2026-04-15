package cn.forgeeks.domt.service;

import cn.forgeeks.domt.entity.Bedroom;
import cn.forgeeks.domt.entity.Student;
import cn.forgeeks.domt.exception.BusinessException;
import cn.forgeeks.domt.mapper.BedroomMapper;
import cn.forgeeks.domt.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class StatisticsService {

    private final StudentMapper studentMapper;
    private final BedroomMapper bedroomMapper;

    public StatisticsService(StudentMapper studentMapper, BedroomMapper bedroomMapper) {
        this.studentMapper = studentMapper;
        this.bedroomMapper = bedroomMapper;
    }

    public void allocateStudent(String studentId, String bedroomId) {
        Student student = studentMapper.getById(studentId);
        Bedroom newBedroom = bedroomMapper.getById(bedroomId);

        if (student == null) throw new BusinessException("学生不存在");
        if (newBedroom == null) throw new BusinessException("宿舍不存在");

        String[] parts = newBedroom.getTotalBed().split("/");
        int current = Integer.parseInt(parts[0]);
        int max = Integer.parseInt(parts[1]);

        if (current >= max) throw new BusinessException("该宿舍已满员");

        // Decrement old bedroom occupancy if student was previously assigned
        if (student.getBedroomId() != null && !student.getBedroomId().isEmpty()) {
            Bedroom oldBedroom = bedroomMapper.getById(student.getBedroomId());
            if (oldBedroom != null) {
                String[] oldParts = oldBedroom.getTotalBed().split("/");
                int oldCurrent = Integer.parseInt(oldParts[0]);
                int oldMax = Integer.parseInt(oldParts[1]);
                oldCurrent = Math.max(0, oldCurrent - 1);
                oldBedroom.setTotalBed(oldCurrent + "/" + oldMax);
                oldBedroom.setStatus("N");
                bedroomMapper.update(oldBedroom);
            }
        }

        // Increment new bedroom occupancy
        current++;
        newBedroom.setTotalBed(current + "/" + max);
        newBedroom.setStatus(current >= max ? "Y" : "N");
        bedroomMapper.update(newBedroom);

        // Update student assignment
        student.setBedroomId(bedroomId);
        student.setBedroomName(newBedroom.getBedroomName());
        student.setStatus("Y");
        studentMapper.update(student);
    }

    public void deallocateStudent(String studentId) {
        Student student = studentMapper.getById(studentId);
        if (student == null) throw new BusinessException("学生不存在");
        if (student.getBedroomId() == null || student.getBedroomId().isEmpty()) {
            throw new BusinessException("该学生未分配宿舍");
        }

        Bedroom bedroom = bedroomMapper.getById(student.getBedroomId());
        if (bedroom != null) {
            String[] parts = bedroom.getTotalBed().split("/");
            int current = Math.max(0, Integer.parseInt(parts[0]) - 1);
            int max = Integer.parseInt(parts[1]);
            bedroom.setTotalBed(current + "/" + max);
            bedroom.setStatus("N");
            bedroomMapper.update(bedroom);
        }

        student.setBedroomId("");
        student.setBedroomName("");
        student.setStatus("N");
        studentMapper.update(student);
    }

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalStudents", studentMapper.count(null, null, null, null, null, null, null, null));
        stats.put("allocatedStudents", studentMapper.countByStatus("Y"));
        stats.put("unallocatedStudents", studentMapper.countByStatus("N"));
        stats.put("maleStudents", studentMapper.countBySex("男"));
        stats.put("femaleStudents", studentMapper.countBySex("女"));
        return stats;
    }
}
