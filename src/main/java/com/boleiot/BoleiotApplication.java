package com.boleiot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boleiot.mapper")
public class BoleiotApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoleiotApplication.class, args);
    }
}
