package com.compass.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * dubbo接口
 *
 * @author sol
 * @create 2019-07-16  19:13
 */
@Controller
@RequestMapping("/dubbo")
@Slf4j
public class DubboController {

    @RequestMapping("/dubboPage")
    public ModelAndView DubboPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tool/dubbo");
        return modelAndView;
    }
}
