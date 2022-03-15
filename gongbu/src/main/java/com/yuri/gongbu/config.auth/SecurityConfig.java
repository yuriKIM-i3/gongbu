package com.yuri.gongbu.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.yuri.gongbu.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests().antMatchers("/", "/word/list/**", "/word/detail/**", "/loginPage", "/css/**", "/image/**").permitAll()
                    .antMatchers("/word/add", "/word/edit/**", "/word/delete/**", "/word/like").hasRole(Role.MEMBER.name())
                    .anyRequest().authenticated()
                .and()
		            .formLogin().loginPage("/loginPage")
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}