package cn.forgeeks.domt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.forgeeks.domt.mapper")
public class DomtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomtApplication.class, args);
    }
}
