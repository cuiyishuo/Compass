package com.compass.controller;

import com.compass.entity.User;
import com.compass.service.UserService;
import com.compass.vo.LoginUser;
import com.compass.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 登录模块
 *
 * @author sol
 * @create 2019-04-25  23:43
 */
@Controller
@RequestMapping("/login")
@SessionAttributes(value = {"loginUser"})
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/registeredPage")
    public ModelAndView registeredPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/registered");
        return modelAndView;
    }

    /**
     * 注册接口，检查不为空后传入数据库做查重校验，不重复，注册成功
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(@Valid User user, BindingResult bindingResult,ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        //  判断是否字段有错误
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            // 将异常信息传给freemarker
            modelAndView.addObject("msg", msg);
            // 回写已经传入的字段
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/login/registeredPage");
            return modelAndView;
        }
        modelAndView.addObject("msg", "注册成功");
        // 如果不为空则传入数据库进行校验
        ResponseMessage responseMessage = userService.register(user);
        if(!responseMessage.isSuccess()){
            modelAndView.addObject("msg", responseMessage.getMsg());
            modelAndView.setViewName("/login/registeredPage");
            return modelAndView;
        }
        // 如果不为空则传入数据库进行校验,看数据库中是否存在
        ResponseMessage responseMessage2 = userService.checkUser(user);
        // 拿到checkUser成功后返回的obkect：loginUser
        LoginUser loginUser = (LoginUser) responseMessage2.getObj();
        // 将拿到的loginUser对象添加到session中，参数一为session的key名，参数二是传进去的数据
        modelMap.addAttribute("loginUser", loginUser);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/loginPage")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/login");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        //  判断是否字段有错误,过滤掉邮箱拦截，因为登录不需要邮箱
        if (bindingResult.hasErrors()&&!bindingResult.getFieldError().getDefaultMessage().startsWith("邮箱")) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            // 将异常信息传给freemarker
            modelAndView.addObject("msg", msg);
            // 回写已经传入的字段
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/login/loginPage");
            return modelAndView;
        }
        // 如果不为空则传入数据库进行校验,看数据库中是否存在
        ResponseMessage responseMessage = userService.checkUser(user);
        if(!responseMessage.isSuccess()){
            modelAndView.addObject("msg", responseMessage.getMsg());
            modelAndView.setViewName("/login/loginPage");
            return modelAndView;
        }
        // 拿到checkUser成功后返回的obkect：loginUser
        LoginUser loginUser = (LoginUser) responseMessage.getObj();
        // 将拿到的loginUser对象添加到session中，参数一为session的key名，参数二是传进去的数据
        modelMap.addAttribute("loginUser", loginUser);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/loginout")
    public ModelAndView loginOut(SessionStatus sessionStatus) {
        ModelAndView modelAndView = new ModelAndView();
        sessionStatus.setComplete();
        modelAndView.setViewName("redirect:/login/loginPage");
        return modelAndView;
    }
}
