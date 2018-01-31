package com.boleiot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableWebSocket
@MapperScan("com.boleiot.mapper")
public class BoleiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoleiotApplication.class, args);
    }

}
