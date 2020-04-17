package org.example.log;

import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: hejz
 * @Description: 审计日志拦截器
 * @Date: 2020/4/17 11:10
 */
@Component
public class AudiInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuditRepository auditRepository;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuditLog log = new AuditLog();
        log.setMethod(request.getMethod());
        log.setPath(request.getContextPath());
        log.setPath(request.getRequestURI());

        User user = (User) request.getAttribute("user");
        if (null != user) {
            log.setUsername(user.getUsername());
        }
        log = auditRepository.save(log);
        request.setAttribute("auditLogId", log.getId());
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Long auditLogId = (Long) request.getAttribute("auditLogId");
        AuditLog auditLog = auditRepository.findById(auditLogId).get();
        auditLog.setStatus(response.getStatus());
        auditRepository.save(auditLog);
    }
}
