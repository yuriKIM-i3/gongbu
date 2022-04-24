package com.yuri.gongbu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.lang.Object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuri.gongbu.config.auth.dto.SessionUser;

public class LogInterceptor implements HandlerInterceptor {    

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        int userId = -1;
        String userName = "null";
        if (!Objects.isNull(session)) {
            SessionUser user = (SessionUser) session.getAttribute("user");
            if (!Objects.isNull(user)) {
                userId = user.getUserId();
                userName = user.getUserName();
            }
        }        

        logger.info("method : {}, url : {}, userId: {}, userName: {}", request.getMethod(), request.getRequestURL(), userId, userName);
        
        return true;
    }
}