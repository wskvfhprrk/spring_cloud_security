package com.hejz.security.filter;

import com.hejz.security.entity.AuditLog;
import com.hejz.security.entity.User;
import com.hejz.security.resitory.AuditLogResitory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuditLogResitory auditLogResitory;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(request.getMethod());
        auditLog.setPath(request.getRequestURI());
        if (request.getAttribute("user") != null) {
            User user = (User) request.getAttribute("user");
            auditLog.setUsername(user.getUsername());
        }
        auditLogResitory.save(auditLog);
        request.setAttribute("auditLogId", auditLog.getId());
        return true;
    }

    /**
     * 此处不建议使用post请求，如果使用post请求的话，有异常信息不会记录的，只会记录成功信息
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Optional<AuditLog> auditLogId = auditLogResitory.findById((Long) request.getAttribute("auditLogId"));
        AuditLog auditLog = auditLogId.get();
        auditLog.setStatus(response.getStatus());
        auditLogResitory.save(auditLog);
    }

}
