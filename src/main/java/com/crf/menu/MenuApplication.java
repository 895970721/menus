package com.crf.menu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.crf.menu.mapper")
@SpringBootApplication
public class MenuApplication {
    public static void main(String[] args){
        SpringApplication.run(MenuApplication.class, args);
    }
}
