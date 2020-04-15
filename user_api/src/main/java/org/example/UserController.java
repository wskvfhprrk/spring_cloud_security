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
    public User create(@RequestBody User user) {
        user = userService.save(user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        user = userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{username}")
    public User query(@PathVariable String username) {
        return userService.findByUsername(username);
    }

}
