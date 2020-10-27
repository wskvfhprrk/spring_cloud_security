package com.hejz.order.sever.reource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class Oauth2ResourceServer extends ResourceServerConfigurerAdapter {

    /**
     * 告诉认证服务器自己resourceId
     * @param resources
     * @throws Exception
     */
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源服务器的id——resourceId
        resources.resourceId("order-server");
    }


    public void configure(HttpSecurity http) throws Exception {
        //这里配置所有都需要权限认证，如果需要不用认证权限在此配置
//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET).access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST).access("#oauth2.hasScope('write')");
    }
}
