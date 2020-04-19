# 服务安全
## 1、防止脚本注入风险
一般防止mybatis注入风险使用#{}入参数，不要使用${}接收参数，防止注入，使用jpa不存在sql注入风险。
## 安全策略（顺序）
1、限流：使用fifter进行限流——实现`OncePerRequestFilter`接口，使用`guava`的`ratelimiter`进行限流。
2、认证：实现`OncePerRequestFilter`接口,要与限流进行排序`order`，后期要使用框架`sucurty`,
3、审计:它不仅要记录访问，同时要记录响应结果，使用`Interceptor`来记录——继承`HandlerInterceptorAdapter`。
4、权限：权限位于审计之后
