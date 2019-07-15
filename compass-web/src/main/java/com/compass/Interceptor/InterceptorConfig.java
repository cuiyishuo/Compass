package com.compass.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器的配置类
 *
 * @author sol
 * @create 2019-03-06  19:28
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 重写父类的增加拦截器的方法
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInterceptor myInterceptor = new MyInterceptor();
        // 将自己写的拦截器加到拦截的链路中，并加入需要拦截以及不拦截的路径
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                // 登录注册相关页为重定向页面，所以不用拦截，不用做拦截判断
                .excludePathPatterns("/login/login").excludePathPatterns("/login/loginPage")
                .excludePathPatterns("/login/register").excludePathPatterns("/login/registeredPage");
        super.addInterceptors(registry);
    }
}
