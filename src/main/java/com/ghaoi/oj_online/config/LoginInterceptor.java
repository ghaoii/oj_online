package com.ghaoi.oj_online.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 先获取session
        HttpSession session = request.getSession(false);
        // 检测用户是否已经登录,返回true就继续执行业务代码，返回false就直接返回前端
        if(session == null || session.getAttribute("user") == null) {
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
