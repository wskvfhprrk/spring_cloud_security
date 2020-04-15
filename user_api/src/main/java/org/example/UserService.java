package org.example;

public interface UserService {

    User save(User user);

    User update(User user);

    void delete(Long id);

    User findByUsername(String username);
}
