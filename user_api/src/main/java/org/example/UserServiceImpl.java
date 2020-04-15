package org.example;

import org.springframework.beans.BeanUtils;
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

    public User save(UserInfo user) {
        User u=new User();
        BeanUtils.copyProperties(user,u);
        User save = userRepository.save(u);
        return save;
    }

    public User update(UserInfo user) {
        User u=new User();
        BeanUtils.copyProperties(user,u);
        User save = userRepository.save(u);
        return save;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserInfo findByUsername(String username) {
        User u = userRepository.findByUsername(username);
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(u,userInfo);
        return userInfo;
    }
}
