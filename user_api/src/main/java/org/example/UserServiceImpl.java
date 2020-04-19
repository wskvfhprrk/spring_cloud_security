package org.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        User u = new User();
        BeanUtils.copyProperties(user, u);
        User save = userRepository.save(u);
        return save;
    }

    public User update(UserInfo user) {
        User u = new User();
        BeanUtils.copyProperties(user, u);
        User save = userRepository.save(u);
        return save;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserInfo findByUsername(String username) {
        User u = userRepository.findByUsername(username);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(u, userInfo);
        return userInfo;
    }

    @Override
    public void login(UserLoginDto user, HttpServletRequest request) {
        User byUsername = userRepository.findByUsername(user.getUsername());
        if (StringUtils.contains(byUsername.getPassword(), user.getPassword())) {
            //防止session固定攻击:如果session不是空值时把原来的session失效，生成新的session
            HttpSession session = request.getSession(false);
            if(session!=null){
                session.invalidate();
            }
            request.getSession().setAttribute("user", user);
        }
    }
}
