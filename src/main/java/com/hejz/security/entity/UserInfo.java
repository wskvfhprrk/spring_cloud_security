package com.hejz.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserInfo {
    private Long id;
    private String name;
    private String username;
    private String password;
}
