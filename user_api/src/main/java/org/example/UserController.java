package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: hejz
 * @Description: 人员信息控制器
 * @Date: 2020/4/15 12:47
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public void login(UserLoginDto user, HttpServletRequest request) {
        userService.login(user, request);
    }

    @PostMapping
    public UserInfo create(@RequestBody @Validated UserInfo user) {
        User u = userService.save(user);
        user.setId(u.getId());
        return user;
    }

    @PutMapping
    public UserInfo update(@RequestBody @Validated UserInfo user) {
        User u = userService.update(user);
        user.setId(u.getId());
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{username}")
    public UserInfo query(@PathVariable String username) {
        return userService.findByUsername(username);
    }

}
