package com.compass.vo;

import lombok.Data;

/**
 * dubbo接口对象
 *
 * @author sol
 * @create 2019-08-02  17:21
 */
@Data
public class DubboApi {
    String ip;
    String param;
    String interfaceName;
    String methodName;
    int timeOut;
    String encoding;
}
