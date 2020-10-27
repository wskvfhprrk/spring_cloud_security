package com.hejz.order.sever.reource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * security安全配置
 */
@Configuration
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 去
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenServices(){
        RemoteTokenServices services=new RemoteTokenServices();
        services.setClientId("orderServer");
        services.setClientSecret("123456");
        services.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
        return services;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //使用重写的oAuth2AuthenticationManager替代默认的AuthenticationManager
        OAuth2AuthenticationManager oAuth2AuthenticationManager=new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setTokenServices(tokenServices());
        return oAuth2AuthenticationManager;
    }
}
