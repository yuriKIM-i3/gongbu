package com.yuri.gongbu.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class LayOutConfig {
	
	// thymeleaf layout
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
}