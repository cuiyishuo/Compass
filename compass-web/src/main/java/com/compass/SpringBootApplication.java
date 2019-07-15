package com.compass;

import org.springframework.boot.SpringApplication;

/**
 * 启动类
 *
 * @author sol
 * @create 2019-04-19  18:36
 */

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    /**
     * 这个启动类一定要放在上层包包含子包
     * 子包就是所谓的com.longteng.lesson.xxx,所以要将Application类要放在com.longteng.lesson包下。
     **/
    public static void main(String[] args) {

        SpringApplication.run(SpringBootApplication.class, args);
    }

}
