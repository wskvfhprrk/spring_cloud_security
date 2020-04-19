package org.example.fifter;

import lombok.extern.slf4j.Slf4j;
import org.example.User;
import org.example.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: hejz
 * @Description: 权限拦截器
 * @Date: 2020/4/18 19:28
 */
@Component
@Slf4j
public class ActInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User user = (User) request.getAttribute("user");
        if (user == null) {
            log.info("没有登陆");
            response.setCharacterEncoding("test/plain");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("unauthorized");
            response.getWriter().flush();
            return false;
        }
        String method = request.getMethod();
        if (!user.hasPermission(method)) {
            log.info("没有用户权限");
            response.setCharacterEncoding("test/plain");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("forbidden");
            response.getWriter().flush();
            return false;
        }
        return true;
    }
}
