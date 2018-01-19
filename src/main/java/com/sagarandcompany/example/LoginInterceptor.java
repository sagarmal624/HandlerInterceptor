package com.sagarandcompany.example;


import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        RequestMapping rm = ((HandlerMethod) handler).getMethodAnnotation(
                RequestMapping.class);
//        Integer personId = ServletRequestUtils.getIntParameter(request, "personId", 0);

        boolean alreadyLoggedIn = request.getSession()
                .getAttribute("user") != null;
        boolean loginPageRequested = rm != null && rm.value().length > 0 && "login" .equals(
                rm.value()[0]);


        if (alreadyLoggedIn && loginPageRequested) {
            response.sendRedirect(request.getContextPath() + "/app/main-age");
            return false;
        } else if (!alreadyLoggedIn && !loginPageRequested) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}