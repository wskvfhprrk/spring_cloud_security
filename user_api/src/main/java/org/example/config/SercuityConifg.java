package org.example.config;

import org.example.log.AudiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: hejz
 * @Description: 添加过滤器拦截器配置
 * @Date: 2020/4/17 11:36
 */
@Configuration
@EnableJpaAuditing
public class SercuityConifg implements WebMvcConfigurer {

    @Autowired
    private AudiInterceptor audiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(audiInterceptor);
        //……可以按顺序添加多个拦截器
    }
}
