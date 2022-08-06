package com.yuri.gongbu.interceptor;

import com.yuri.gongbu.config.auth.dto.SessionUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Object;
import java.util.Objects;

public class SettingUserInfoInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // ログインしたユーザーが存在するなら、ユーザー情報をviewに渡す
        HttpSession session = request.getSession(false);

        if (!Objects.isNull(session)) {
            SessionUser user = (SessionUser) session.getAttribute("user");
            // 静的リソースはmodelAndViewがnull
            if (Objects.nonNull(modelAndView)) {
                modelAndView.addObject("user", user);
            }
        }
    }

}
