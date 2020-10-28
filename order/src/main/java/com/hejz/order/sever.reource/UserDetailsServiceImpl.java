package com.hejz.order.sever.reource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=new User();
        //应该从数据库查出user来
        user.setPassword(s);
        user.setId(1L);
        user.setPassword("123456");
        return user;
    }
}
