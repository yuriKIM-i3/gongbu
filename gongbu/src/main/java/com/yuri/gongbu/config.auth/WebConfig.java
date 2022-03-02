package com.yuri.gongbu.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

import com.yuri.gongbu.filter.LoginCheckInterceptor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    /**
    LoginUserArgumentResolverがspringで認識できるよう、WebMvcConfigurerへ追加
    */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }

    /**
    LoginCheckInterceptorのインターセプト登録
     */
    @Override
 	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) 
 				.order(1) 
 				.addPathPatterns("/**") 
 				.excludePathPatterns("/css/**", "/image/**", "/*.html", "/error"); 
    }
}