package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: hejz
 * @Description:
 * @Date: 2020/4/15 12:47
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserInfo create(@RequestBody UserInfo user) {
        User u = userService.save(user);
        UserInfo userInfo=new UserInfo();
        userInfo.setId(u.getId());
        return userInfo;
    }

    @PutMapping
    public UserInfo update(@RequestBody UserInfo user) {
        User u = userService.update(user);
        UserInfo userInfo=new UserInfo();
        userInfo.setId(u.getId());
        return userInfo;
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
