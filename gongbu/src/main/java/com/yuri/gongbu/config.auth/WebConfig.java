package com.yuri.gongbu.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;
import java.util.Arrays;

import com.yuri.gongbu.filter.SettingUserInfoInterceptor;
import com.yuri.gongbu.filter.UserCheckInterceptor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    public List resources
            = Arrays.asList("/css/**", "/image/**", "/*.html");
    
    /**
    LoginUserArgumentResolverがspringで認識できるよう、WebMvcConfigurerへ追加
    */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }

    /**
    インターセプト登録
     */
    @Override
 	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SettingUserInfoInterceptor()) 
 				.order(1) 
 				.addPathPatterns("/**") 
 				.excludePathPatterns(resources)
                .excludePathPatterns("/error");

        registry.addInterceptor(new UserCheckInterceptor()) 
 				.order(2) 
 				.addPathPatterns("/word/add", "/word/edit/**", "/word/delete/**", "/word/like/**") 
                .excludePathPatterns("/", "/loginPage", "/word/list", "/word/detail/**", "/error"); 
    }
}