package com.compass.service;

import com.compass.util.TelnetSocket;
import com.compass.vo.DubboApi;
import com.compass.vo.ResponseMessage;
import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.WebSphereUowTransactionManager;

import java.io.IOException;
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
     *
     * @param zkAddress
     * @param interfaceName
     * @return
     */
    public ResponseMessage resolveIp(String zkAddress, String interfaceName) {
        ZkClient zkClient = null;
        try {
            // 超过3s则抛出异常
            zkClient = new ZkClient(zkAddress, 3000);
        } catch (ZkTimeoutException z) {
            return ResponseMessage.errorResponse("注册中心链接异常，请确认地址是否正确");
        }
        List<String> dubboNodeList;
        try {
            dubboNodeList = zkClient.getChildren("/dubbo/" + interfaceName.trim() + "/providers");
        } catch (ZkNoNodeException z) {
            return ResponseMessage.errorResponse("请确认接口是否存在，接口名称：" + interfaceName);
        }

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

    /**
     * 通过接口ip和接口名称解析出接口下的方法
     *
     * @param ip
     * @param interfaceName
     * @return
     */
    public ResponseMessage resolveMethod(String ip, String interfaceName) {
        TelnetSocket telnetSocket = null;
        List<String> methodList = new ArrayList<String>();
        // 将传进来的ip拆分为ip和port
        String[] ips = ip.split(":");
        try {
            // 通过telnet链接zk
            telnetSocket = new TelnetSocket(ips[0], Integer.valueOf(ips[1]), 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 获得返回的信息
            String result = telnetSocket.send("ls -l " + interfaceName);
            System.out.println("before:" + result);
            result = result.replace("dubbo>", "");
            System.out.println("after:" + result);
            // 将返回的数据以换行符查分成数组
            String[] methods = result.split("\r\n");
            // 将每一行的方法名分离出来，通过空格处的索引位置begin
            for (String method : methods) {
                System.out.println("method:" + method);
                method = method.substring(method.indexOf(" "));
                methodList.add(method);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseMessage.successResponse(methodList);
    }

    public String invoke(DubboApi dubboApi) {
        String[] ips = dubboApi.getIp().split(":");
        TelnetSocket telnetSocket = null;
        try {
            telnetSocket = new TelnetSocket(ips[0], Integer.parseInt(ips[1]));
            telnetSocket.setReadEncoding(dubboApi.getEncoding());
            telnetSocket.setWriteEncoding(dubboApi.getEncoding());
            String methodName = dubboApi.getMethodName().substring(0, dubboApi.getMethodName().indexOf("(")).trim();
            String cmd = "invoke " + dubboApi.getInterfaceName() + "." + methodName + "(" + dubboApi.getParam() + ")";
            String result = telnetSocket.send(cmd);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
