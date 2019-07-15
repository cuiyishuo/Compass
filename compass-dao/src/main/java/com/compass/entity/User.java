package com.compass.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户
 *
 * @author sol
 * @create 2019-05-04  11:18
 */
@Data
public class User {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String passWord;
    @NotBlank(message = "邮箱不能为空")
    private String email;
}
