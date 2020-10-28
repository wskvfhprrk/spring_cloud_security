package com.hejz.order.sever.reource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.*;

/**
 * security安全配置
 */
@Configuration
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
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
        //使用自定义的userDetailsService
        services.setAccessTokenConverter(GetAccessTokenCoverter());
        return services;
    }

    private AccessTokenConverter GetAccessTokenCoverter() {
        DefaultAccessTokenConverter accessTokenConverter=new DefaultAccessTokenConverter();
        DefaultUserAuthenticationConverter userTokenConverter=new DefaultUserAuthenticationConverter();
        //使用自定义的userDetailsService
        userTokenConverter.setUserDetailsService(userDetailsService);
        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        return accessTokenConverter;
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
