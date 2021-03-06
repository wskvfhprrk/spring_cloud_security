# spring cloud微服务安全

## API安全

##### 什么是API

应用程序编程接口，即为API

##### API安全有哪几个方面

网络安全、信息安全、应用安全

##### API安全目标：CIA

- 机密性：确保信息只预期的读者访问
- 完整性：防止未授权的创建、修改、删除
- 可用性：当用户需要访问API时，API总是可用的

##### 常见的API风险：

- 欺骗：伪装成基本人；
- 干预：将不钫被 修改的数据、消息或设置改掉；
- 否认：拒绝承认做过的事；
- 信息泄露：奖你希望保密信息披露出来；
- 拒绝服务：阻止用户访问信息和服务；
- 越权：做了你不希望他做的事

##### 风险与安全机制的对应关系

- 认证：（欺骗）确保你的用户或客户端真的是他们自己
- 授权：（信息泄漏、干预、越权）确保每个针对API的访问都是经过授权的
- 审计：（否认）防止用户请求淹没你的API
- 加密：（信息泄漏）确保出入API的数据 是私密的

##### 业务处理安全机制的流程：

- 加密：从浏览器或APP访问开始进行加密请求，在网关解决密
- 流控：排在第二位
- 认证：第三位
- 审计：第四位
- 授权：第五位

## 注入攻击

#####  如何进行攻击的

通过sql脚本对数据进行修改

##### 危害程序

- 注入攻击排在网络安全的首位；
- 危害非常大，严重的会有删除库和数据的风险。

##### 如何预防注入攻击

- 传入参数进行校验（比如正则表达式校验）
- 使用数据库用户权限，生产数据库不使用root，预防被删库风险
- 使用JPA持久层框架，甚用mybatis,mybatis框架使用时一般使用`#{}`脚本，而`${}`脚本传值不建议使用，有注入风险
- 不建议直接使用jdbcTemplate,直接写sql传参数

### 审计

###### 目的

审计为了防止用户操作后否认而进行的操作记录，也是日志

###### 系统位置

放在认证之后，授权之前

###### 审计注意

审计不使用过滤器，使用拉截器比较好处理，因为审计需要有返回结果，在使用拦截器时注意`使用after不使用post方法处理返回结果，post方法异常信息不会走，after方法无论成功与否，都会走`，能够拿到返回值。



## 微服务安全

##### 微服务安全面临的挑战

- 更多的入口点，更高的安全风险
- 性能问题
- 服务间的通讯
- 跨多个微服务的请求难以追踪
- 容器化部署导致的访问和证书控制问题
- 如何在微服务音共享用户登陆状态
- 多语言架构要求每一个团队都有一定的安全经验

### 微服务安全使用springCloudOauth2框架

###### 使用自定义的userDetails获取用户信息其它值

1. 建一个User实现userDetails（包含有其它信息——所需要用户的信息）；
2. 建一个userDetailsServiceImpl实现UserDetailsService,引用已经建立好的User（需要在数据库中查询其值）；
3. 在WebSecurityConfigurerAdapter适配器类中引用自定义的UserDetailsService:

```java
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
```

