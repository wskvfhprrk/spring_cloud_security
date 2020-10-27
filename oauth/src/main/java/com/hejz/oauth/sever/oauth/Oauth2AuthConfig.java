package com.hejz.oauth.sever.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    //配置客户端详情服务
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //客户端名称
                .withClient("orderApp")
                //客户端密码
                .secret(passwordEncoder.encode("123456"))
                //请求的权限
                .scopes("read", "write")
                //token时效
                .accessTokenValiditySeconds(3600)
                //可访问那些资源服务器
                .resourceIds("order-server")
                //授权方式——四种
                .authorizedGrantTypes("password")
                .and()
                //客户端名称
                .withClient("orderServer")
                //客户端密码
                .secret(passwordEncoder.encode("123456"))
                //请求的权限
                .scopes("read")
                //token时效
                .accessTokenValiditySeconds(3600)
                //可访问那些资源服务器
                .resourceIds("order-server")
                //授权方式——四种
                .authorizedGrantTypes("password");
    }

    //客户身份校验
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    //权限规则
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }
}
