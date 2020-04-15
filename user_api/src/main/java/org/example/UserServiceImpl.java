package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hejz
 * @Description:
 * @Date: 2020/4/15 13:17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        User save = userRepository.save(user);
        return save;
    }

    public User update(User user) {
        User save = userRepository.save(user);
        return save;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        User u = userRepository.findByUsername(username);
        return u;
    }
}
