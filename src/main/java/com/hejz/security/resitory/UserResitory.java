package com.hejz.security.resitory;

import com.hejz.security.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface UserResitory extends PagingAndSortingRepository<User, Long> {
    List<User> findByUsername(String username);
}
