package com.example.oauth.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author: hejz
 * @Description:
 * @Date: 2020/4/20 20:16
 */
@Configuration
@EnableAuthorizationServer
public class OAuthAuthServerconfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //客户端——postman
                .withClient("orderApp")
                .secret(passwordEncoder.encode("123456"))
                //权限识别为
                .scopes("read", "write")
                //可以访问的资源服务器有那些，以英文逗号隔开
                .resourceIds("order-server")
                .accessTokenValiditySeconds(3600)
                .authorizedGrantTypes("password")
                .and()
                .withClient("order-server")
                .secret(passwordEncoder.encode("123456"))
                .scopes("read")
                .redirectUris("price-server")
                .accessTokenValiditySeconds(3600)
                .authorizedGrantTypes("password");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }
}
