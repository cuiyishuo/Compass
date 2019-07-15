package com.compass;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sol
 * @create 2019-04-19  19:01
 */
@Controller
public class MainController {

    @RequestMapping({"/","/index"})
    public String s() {
        return "index";
    }

    @RequestMapping("/layout")
    public String l() {
        return "/layout/layout";
    }
}
