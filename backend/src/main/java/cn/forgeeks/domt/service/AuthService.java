package cn.forgeeks.domt.service;

import cn.forgeeks.domt.dto.LoginResponse;
import cn.forgeeks.domt.entity.Manager;
import cn.forgeeks.domt.entity.Student;
import cn.forgeeks.domt.exception.BusinessException;
import cn.forgeeks.domt.mapper.ManagerMapper;
import cn.forgeeks.domt.mapper.StudentMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ManagerMapper managerMapper;
    private final StudentMapper studentMapper;

    public AuthService(ManagerMapper managerMapper, StudentMapper studentMapper) {
        this.managerMapper = managerMapper;
        this.studentMapper = studentMapper;
    }

    public LoginResponse login(String username, String password) {
        // Check root admin
        if ("root".equals(username) && "root".equals(password)) {
            LoginResponse resp = new LoginResponse();
            resp.setName("root");
            resp.setType("root");
            return resp;
        }

        // Check manager
        Manager manager = managerMapper.findByNameAndPassword(username, password);
        if (manager != null) {
            LoginResponse resp = new LoginResponse();
            resp.setName(manager.getManagerName());
            resp.setType("manager");
            resp.setUser(manager);
            return resp;
        }

        // Check student
        Student student = studentMapper.findByNameAndPassword(username, password);
        if (student != null) {
            LoginResponse resp = new LoginResponse();
            resp.setName(student.getStudentName());
            resp.setType("student");
            resp.setUser(student);
            return resp;
        }

        throw new BusinessException("用户名或密码错误");
    }
}
