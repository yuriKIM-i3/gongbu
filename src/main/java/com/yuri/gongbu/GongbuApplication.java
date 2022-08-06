package com.yuri.gongbu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GongbuApplication {

    public static void main(String[] args) {
        SpringApplication.run(GongbuApplication.class, args);
    }

}
