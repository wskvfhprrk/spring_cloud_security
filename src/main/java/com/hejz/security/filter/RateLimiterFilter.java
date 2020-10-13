package com.hejz.security.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 限流过滤器
 */
@Component
@Order(1)
public class RateLimiterFilter extends OncePerRequestFilter {
    /**
     * 建一个每秒限定仅一个请求的RateLimiter
     */
    private RateLimiter rateLimiter=RateLimiter.create(1);

    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //如果请求通过，就放行，如果请求不通过就限流
        if(rateLimiter.tryAcquire()){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else {
            //使用utf-8编码，不然中文乱码
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpServletResponse.getWriter().write("服务器太忙了！！！");
            httpServletResponse.getWriter().flush();
            return;
        }
    }
}
