# 服务安全
## 1、防止脚本注入风险
一般防止mybatis注入风险使用#{}入参数，不要使用${}接收参数，防止注入，使用jpa不存在sql注入风险。
## 安全策略（顺序）
1、限流：使用fifter进行限流——实现`OncePerRequestFilter`接口，使用`guava`的`ratelimiter`进行限流。
2、认证：实现`OncePerRequestFilter`接口,要与限流进行排序`order`，后期要使用框架`sucurty`,
3、审计:它不仅要记录访问，同时要记录响应结果，使用`Interceptor`来记录——继承`HandlerInterceptorAdapter`。
4、权限：权限位于审计之后使用拦截器处理
### 框架安全处理
#### session 固定攻击

防止session固定攻击:如果session不是空值时把原来的session失效，生成新的session
```java
            HttpSession session = request.getSession(false);
            if(session!=null){
                session.invalidate();
            }
            request.getSession().setAttribute("user", user);
```
## oauth2认证
### oauth2认证原理
   
  用户
