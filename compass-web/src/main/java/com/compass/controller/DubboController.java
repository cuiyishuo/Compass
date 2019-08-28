package com.compass.controller;

import com.compass.service.DubboService;
import com.compass.vo.DubboApi;
import com.compass.vo.ResponseMessage;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;

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

    @Autowired
    DubboService dubboService;

    @RequestMapping("/dubboPage")
    public ModelAndView DubboPage(ModelMap modelMap) {
        modelMap.addAttribute("zkAddress", "47.105.55.243:2181");
        modelMap.addAttribute("interfaceName", "com.longteng.service.DubboTestService");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tool/dubbo");
        return modelAndView;
    }

    /**
     * 解析接口的api
     * @param zkAddress
     * @param interfaceName
     * @return
     */
    @RequestMapping("/resolveIp")
    public @ResponseBody
    ResponseMessage resolveIp(String zkAddress, String interfaceName) {
        if (StringUtil.isEmpty(zkAddress)) {
            return ResponseMessage.errorResponse("zk地址不能为空");
        }
        if (StringUtil.isEmpty(interfaceName)) {
            log.error("接口地址不能为空");
            return ResponseMessage.errorResponse("接口名不能为空");
        }
        // 调用service解析ip的方法
        ResponseMessage responseMessage = dubboService.resolveIp(zkAddress, interfaceName);
        return responseMessage;
    }

    /**
     * 解析接口下的方法
     * @param ipPort
     * @param interfaceName
     * @return
     */
    @RequestMapping("/resolveMethod")
    public @ResponseBody
    ResponseMessage resolveMethod(String ipPort, String interfaceName) {
        if (StringUtil.isEmpty(ipPort)) {
            return ResponseMessage.errorResponse("ip地址不能为空");
        }
        if (StringUtil.isEmpty(interfaceName)) {
            return ResponseMessage.errorResponse("接口名不能为空");
        }
        // 调用service解析method的方法
        ResponseMessage responseMessage = dubboService.resolveMethod(ipPort, interfaceName);
        return responseMessage;
    }

    @RequestMapping("/invoke")
    public @ResponseBody
    ResponseMessage invoke(DubboApi dubboApi) {
        String result = dubboService.invoke(dubboApi);
        return ResponseMessage.successResponse(result);
    }
}
