# 服务安全
## 1、防止脚本注入风险
一般防止mybatis注入风险使用#{}入参数，不要使用${}接收参数，防止注入，使用jpa不存在sql注入风险。
## 安全策略（顺序）
1、限流：使用fifter进行限流，使用`guava`的`ratelimiter`进行限流。
2、认证：
3、审记
4、权限
