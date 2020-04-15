package org.example;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author: hejz
 * @Description:
 * @Date: 2020/4/15 12:46
 */
@Data
public class UserInfo {
    private Long id;
    @NotBlank(message = "名字不能为空值")
    private String name;
    @NotBlank(message = "用户名不能为空值")
    private String username;
    @NotBlank(message = "密码不能为空值")
    private String password;
}
