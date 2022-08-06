package com.yuri.gongbu.config.auth;

import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.interceptor.LogInterceptor;
import com.yuri.gongbu.interceptor.SettingUserInfoInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
    インターセプト登録
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SettingUserInfoInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(GlobalVariable.PATH_FOR_STATIC_RESOURCE)
                .excludePathPatterns("/error");

        registry.addInterceptor(new LogInterceptor())
                .order(2)
                .excludePathPatterns(GlobalVariable.PATH_FOR_STATIC_RESOURCE);
    }
}
