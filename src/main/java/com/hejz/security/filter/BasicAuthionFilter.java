package com.hejz.security.filter;

import com.hejz.security.entity.User;
import com.hejz.security.resitory.UserResitory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 认证过滤器——认证不认证通过，都给放行，认证通过可以在request请求中绑定身份参数，也可以绑定其它需要身份参数
 */
@Component
public class BasicAuthionFilter extends OncePerRequestFilter {

    @Autowired
    private UserResitory userResitory;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        //判断是否带了头部权限
        if (StringUtils.isNoneBlank(authorization)) {
            //去掉头部后的密文
            String after = StringUtils.substringAfter(authorization, "Basic ");
            //解析密文
            String s = new String(Base64Utils.decodeFromString(after));
            //根据冒号拆成用户名和密码
            String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(s, ":");
            String username = strings[0];
            String password = strings[1];
            List<User> users = userResitory.findByUsername(username);
            //如果不为空直接取第一个值
            if (!users.isEmpty() &&StringUtils.equals(users.get(0).getPassword(),password) ) {
                httpServletRequest.setAttribute("user", users.get(0));
            }
        }
        //如果没有传值程序继续往下走
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
