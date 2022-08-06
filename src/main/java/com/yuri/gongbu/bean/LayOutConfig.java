package com.yuri.gongbu.bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LayOutConfig {

    // thymeleaf layout
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
