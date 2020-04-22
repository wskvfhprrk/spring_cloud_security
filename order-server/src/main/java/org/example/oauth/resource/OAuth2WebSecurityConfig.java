package org.example.oauth.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author: hejz
 * @Description: sprig-security安全配置
 * @Date: 2020/4/20 21:51
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * token服务器认证
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        //认证地址
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
        //客户端名称
        remoteTokenServices.setClientId("order-server");
        //认证密码
        remoteTokenServices.setClientSecret("123456");
        return remoteTokenServices;
    }

    /**
     * 将期暴露为bean,方便其它方法注入{@Link OAuthAuthServerconfig}
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager auth2AuthenticationManager = new OAuth2AuthenticationManager();
        auth2AuthenticationManager.setTokenServices(tokenServices());
        return auth2AuthenticationManager;
    }
}
