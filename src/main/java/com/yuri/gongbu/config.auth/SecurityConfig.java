package com.yuri.gongbu.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.stream.Stream;
import java.util.Arrays;

import com.yuri.gongbu.domain.user.Role;
import com.yuri.gongbu.global.GlobalVariable;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .requiresChannel().anyRequest().requiresSecure()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers(Stream.concat(Arrays.stream(GlobalVariable.PATH_FOR_ALL), Arrays.stream(GlobalVariable.PATH_FOR_STATIC_RESOURCE)).toArray(String[]::new)).permitAll()
                    .antMatchers(GlobalVariable.PATH_FOR_MEMBER).hasRole(Role.MEMBER.name())
                    .anyRequest().authenticated()
                .and()
		            .formLogin().loginPage("/loginPage").permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}