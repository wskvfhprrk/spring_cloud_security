package com.hejz.security.controller;

import com.hejz.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public User Create(@RequestBody User user) {
        return new User();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        return;
    }

    @PutMapping("{id}")
    public User update(@RequestBody User user) {
        return new User();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return new User();
    }

    @GetMapping
    public List query(String username) {
        String sql = "select id,username from user where username='" + username + "'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
}
