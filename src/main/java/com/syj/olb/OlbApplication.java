package com.syj.olb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@MapperScan("com.syj.olb")
public class OlbApplication {
    public static void main(String[] args) {

        SpringApplication.run(OlbApplication.class, args);
    }

}

