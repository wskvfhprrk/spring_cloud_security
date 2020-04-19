package org.example;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    User save(UserInfo user);

    User update(UserInfo user);

    void delete(Long id);

    UserInfo findByUsername(String username);

    void login(UserLoginDto user, HttpServletRequest request);

}
