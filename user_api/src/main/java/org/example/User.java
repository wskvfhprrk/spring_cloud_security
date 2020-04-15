package org.example;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: hejz
 * @Description:
 * @Date: 2020/4/15 12:46
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
