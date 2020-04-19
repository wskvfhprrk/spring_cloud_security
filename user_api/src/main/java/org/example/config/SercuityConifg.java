package org.example.config;

import org.example.User;
import org.example.fifter.ActInterceptor;
import org.example.log.AudiInterceptor;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

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
    @Autowired
    private ActInterceptor actInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(audiInterceptor);
        registry.addInterceptor(actInterceptor);
        //……可以按顺序添加多个拦截器
    }

    /**
     * jpa使用@CreatedBy或者@LastModifiedBy所需要bean,根据实际需要从sesseion或者缓存中取出当前用户值
     *
     * @return
     * @See <a href="https://blog.csdn.net/DoomHush/article/details/89528342">spring boot 使用JPA @CreatedBy @LastModifiedBy 自动保存操作人</a>
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        User user = (User) requestAttributes.getRequest().getSession().getAttribute("user");
        return () -> Optional.of(user.getUsername());
    }
}
