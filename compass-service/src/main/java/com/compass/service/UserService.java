package com.compass.service;

import com.compass.entity.User;
import com.compass.mapper.UserMapper;
import com.compass.vo.LoginUser;
import com.compass.vo.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 用户数据业务处理
 *
 * @author sol
 * @create 2019-06-13  23:13
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 注册
     * @param user
     * @return
     */
    public ResponseMessage register(User user) {
        // 插入成功会返回插入数量
        try {
            int i = userMapper.register(user);
            if (i == 1) {
                System.out.println("插入成功：" + i);
                return ResponseMessage.successResponse();
            }else{
                return ResponseMessage.errorResponse("注册失败，请联系管理员");
            }
        } catch (DuplicateKeyException d) {
            d.printStackTrace();
            return ResponseMessage.errorResponse("用户名重复，请重新注册");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.errorResponse("注册失败，请联系管理员");
        }
    }

    public ResponseMessage checkUser(User user) {
        User user1 = userMapper.checkUser(user);
        // 如果查询到结果，不为空，证明name和code都在库里有存，可以登录
        if (null == user1) {
            return ResponseMessage.errorResponse("用户不存在或者密码错误");
        }
        // 如果存在则可以登录成功，将user属性信息放到loginUser中，作为其他请求拦截时的校验
        LoginUser loginUser = new LoginUser();
        // 使用beanutil拷贝属性,如果有相同的属性，就会自动拷贝过去，属性名要相同
        //第一个参数是被拷贝的对象，第二个参数是目标拷贝对象
        BeanUtils.copyProperties(user1,loginUser);
        System.out.println(loginUser);
        return ResponseMessage.successResponse(loginUser);

    }

}
