package org.example.fifter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: hejz
 * @Description: 限流过滤器
 * @Date: 2020/4/15 14:03
 */
@Component
@Slf4j
public class RateLimitFifter extends OncePerRequestFilter {
    //ratelimiter限速——每秒访问次数
    private RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (rateLimiter.tryAcquire()) {
            //请求处理过来时
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            log.error("访问初限流了");
            httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpServletResponse.getWriter().write("too many request!!");
            httpServletResponse.getWriter().flush();
            return;
        }
    }
}
