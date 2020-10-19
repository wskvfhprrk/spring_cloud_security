package com.hejz.security.controller;

import com.hejz.security.entity.User;
import com.hejz.security.entity.UserInfo;
import com.hejz.security.resitory.UserResitory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserResitory userResitory;

    @PostMapping
    public UserInfo Create(@RequestBody User user) {
        user = userResitory.save(user);
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        return userInfo;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userResitory.deleteById(id);
        return;
    }

    @PutMapping("{id}")
    public UserInfo update(@RequestBody User user) {
        User save = userResitory.save(user);
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(save,userInfo);
        return userInfo;
    }

    @GetMapping("{id}")
    public UserInfo getById(@PathVariable Long id) {
        User user = userResitory.findById(id).orElse(null);
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        return userInfo;
    }

    @GetMapping
    public List query(String username) {
        List<User> all = userResitory.findByUsername(username);
        List<UserInfo> collect = all.stream().map(s -> {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(s, userInfo);
            return userInfo;
        }).collect(Collectors.toList());
        return collect;
//        String sql = "select id,username from user where username='" + username + "'";
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
//        return maps;
    }
    @GetMapping("findAll")
    public List<UserInfo> findAll(){
        //第一页为0，每页2条，查询
        Pageable pageable=PageRequest.of(0, 2);
        //分页可加入排序功能
        pageable.getSortOr(Sort.by(Sort.Direction.DESC,"username"));
        Page<User> all = userResitory.findAll(pageable);
        List<UserInfo> collect = all.stream().sequential().map(s -> {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(s, userInfo);
            return userInfo;
        }).collect(Collectors.toList());
        throw new RuntimeException("程序错误");
//        return collect;
    }
}
