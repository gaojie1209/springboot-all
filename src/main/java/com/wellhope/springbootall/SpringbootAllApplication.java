package com.wellhope.springbootall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wellhope.springbootall.mapper")
public class SpringbootAllApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootAllApplication.class, args);
    }

}
