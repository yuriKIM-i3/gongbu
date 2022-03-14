package com.yuri.gongbu.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.lang.Object;

import com.yuri.gongbu.config.auth.dto.SessionUser;

public class SettingUserInfoInterceptor implements HandlerInterceptor {    

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // ログインしたユーザーが存在するなら、ユーザー情報をviewに渡す
        HttpSession session = request.getSession(false);

        if (!Objects.isNull(session)) {
            SessionUser user = (SessionUser) session.getAttribute("user");
            modelAndView.addObject("user", user);
        }          
    }

}