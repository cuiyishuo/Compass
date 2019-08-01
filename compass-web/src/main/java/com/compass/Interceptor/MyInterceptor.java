package com.compass.Interceptor;

import com.compass.vo.LoginUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 我自己的拦截器实现HandlerInterceptor 接口
 * 所有配置中的请求都会被拦截器拦截掉
 *
 * @author sol
 * @create 2019-03-06  22:44
 */
public class MyInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession httpSession = request.getSession();
        // 拦截后请求后，获取缓冲中的session中的loginUser，如果不为空则通过，为空则重定向到登录页
        // 接LoginUser中/login接口的添加session
        LoginUser loginUser = (LoginUser) httpSession.getAttribute("loginUser");
        if (null == loginUser) {
            System.out.println("登录失败，没有登录权限，重定向到登录页");
            response.setStatus(302);
            response.sendRedirect("/login/loginPage");
        }
        System.out.println();
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
