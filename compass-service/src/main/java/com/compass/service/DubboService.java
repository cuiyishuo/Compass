package com.compass.service;

import com.compass.vo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * dubbo页面
 *
 * @author sol
 * @create 2019-07-29  23:18
 */
@Slf4j
@Service
public class DubboService {

    // 正则表达式，替换ip
    static Pattern ipPattern = Pattern.compile("(?<=//).*(?=/)");

    /**
     * 通过接口名解析出服务所在的地址和端口
     * @param zkAddress
     * @param interfaceName
     * @return
     */
    public ResponseMessage resolveIp(String zkAddress, String interfaceName) {
        ZkClient zkClient = new ZkClient(zkAddress);
        List<String> dubboNodeList = zkClient.getChildren("/dubbo/" + interfaceName.trim() + "/providers");
        List<String> ipList = new ArrayList<String>();
        for (String s : dubboNodeList) {
            try {
                String url = URLDecoder.decode(s, "UTF-8");
                // 将要匹配的原始字符串传入正则对象的方法中
                Matcher matcher = ipPattern.matcher(url);
                String ip = null;
                if (matcher.find()) {
                    // 拿到匹配到的数据
                    ip = matcher.group();
                }
                // 因为阿里云服务器对外提供跳板机ip所以真实的ip无法访问，如果放到公司内部就可以把这个替换删掉
                // 正则提取到的是127.31.145.55:20880
                ip = ip.replace("172.31.145.55", "47.105.55.243");
                ipList.add(ip);
            } catch (UnsupportedEncodingException e) {
                log.error("解析dubbo的ip异常，当前接口名：{}", interfaceName, e);
            }
        }
        // 将解析到的ip返回
        return ResponseMessage.successResponse(ipList);
    }
}
