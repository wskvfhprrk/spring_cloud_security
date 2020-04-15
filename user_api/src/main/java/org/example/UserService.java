package org.example;

public interface UserService {

    User save(UserInfo user);

    User update(UserInfo user);

    void delete(Long id);

    UserInfo findByUsername(String username);
}
