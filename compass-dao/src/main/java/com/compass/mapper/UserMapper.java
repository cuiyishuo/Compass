package com.compass.mapper;

import com.compass.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作user表的接口
 */
@Mapper
public interface UserMapper {

    // 注册用户
    public int register(User user);

    // 登录校验用户
    public User checkUser(User user);

}
