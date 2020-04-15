package org.example.fifter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.User;
import org.example.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: hejz
 * @Description: 认证过滤器
 * @Date: 2020/4/15 15:01
 */
@Component
@Slf4j
public class BasicAuthcationFifter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authcation = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isNotBlank(authcation)) {
            String base64 = StringUtils.substringAfter(authcation, "Basic ");
            String token = new String(Base64Utils.decodeFromString(base64));
            log.info(token);
            String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");
            User user = userRepository.findByUsername(split[0]);
            if (null != user && StringUtils.equals(user.getPassword(), split[1])) {
                log.info("认证通过");
                httpServletRequest.setAttribute("user", user);
            } else {
                log.error("没有认证通过");
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpServletResponse.getWriter().write("unauthorized");
                httpServletResponse.getWriter().flush();
                return;
            }
        } else {
            log.error("没有认证通过");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getWriter().write("认证未通过");
            httpServletResponse.getWriter().flush();
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
