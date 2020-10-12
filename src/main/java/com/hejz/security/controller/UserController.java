package com.hejz.security.controller;

import com.hejz.security.entity.User;
import com.hejz.security.resitory.UserResitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserResitory userResitory;

    @PostMapping
    public User Create(@RequestBody User user) {
        user = userResitory.save(user);
        return user;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userResitory.deleteById(id);
        return;
    }

    @PutMapping("{id}")
    public User update(@RequestBody User user) {
        User save = userResitory.save(user);
        return save;
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        User user = userResitory.findById(id).orElse(null);
        return user;
    }

    @GetMapping
    public List query(String username) {
        List<User> all = userResitory.findByUsername(username);
        return all;
//        String sql = "select id,username from user where username='" + username + "'";
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
//        return maps;
    }
    @GetMapping("findAll")
    public Iterable<User> findAll(){
        //按username倒序把列
//        Sort sort=Sort.by(Sort.Direction.DESC,"username");
//        Iterable<User> all = userResitory.findAll(sort);
        //第一页为0，每页2条，查询
        Pageable pageable=PageRequest.of(0, 2);
        //分页可加入排序功能
        pageable.getSortOr(Sort.by(Sort.Direction.DESC,"username"));
        Page<User> all = userResitory.findAll(pageable);
        return all;
    }
}
