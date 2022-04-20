package com.yuri.gongbu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.lang.Object;

import com.yuri.gongbu.config.auth.dto.SessionUser;

public class UserCheckInterceptor implements HandlerInterceptor {    

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (!Objects.isNull(session)) {
            SessionUser user = (SessionUser) session.getAttribute("user");

            if (!Objects.isNull(user)) {                
                return true;
            }    
        }        

        response.sendRedirect("/loginPage");
        return false;
    }
}