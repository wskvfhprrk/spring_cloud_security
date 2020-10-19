package com.hejz.security.config;

import com.hejz.security.filter.AuditLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加拦截器启动类
 */
@Configuration
@EnableJpaAuditing
public class SecurityConfig implements WebMvcConfigurer {
    @Autowired
    private AuditLogInterceptor auditLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //多个按顺序依次添加
        registry.addInterceptor(auditLogInterceptor);
    }
}
