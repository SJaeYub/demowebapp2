package com.practice.demowebapp2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.practice.demowebapp2.mapper")
public class Demowebapp2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demowebapp2Application.class, args);
    }

}
