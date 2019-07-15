package com.compass.vo;

import lombok.Data;

/**
 * 响应数据对象
 *
 * @author sol
 * @create 2019-06-16  00:16
 */
@Data
public class ResponseMessage {

    private String msg;
    private Integer code;
    private boolean success;
    private Object obj;

    // 构造方法的重载

    /**
     *
     * @param success 响应是否成功
     * @param code 响应状态码
     * @param obj 响应数据
     */
    ResponseMessage(boolean success, Integer code,Object obj) {
        this.success = success;
        this.code = code;
        this.obj = obj;
    }
    /**
     *
     * @param success 响应是否成功
     * @param code 响应状态码
     * @param msg 响应消息
     */
    ResponseMessage(boolean success, Integer code,String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    /**
     *
     * @param success 响应是否成功
     * @param code 响应状态码
     */
    ResponseMessage(boolean success, Integer code) {
        this.success = success;
        this.code = code;
    }

    // 静态方法
    public static ResponseMessage errorResponse(String msg) {
        return new ResponseMessage(false, -400, msg);
    }

    public static ResponseMessage errorResponse(Integer code,String msg) {
        return new ResponseMessage(false, code, msg);
    }

    public static ResponseMessage successResponse() {
        return new ResponseMessage(true, 200);
    }

    public static ResponseMessage successResponse(Object obj) {
        return new ResponseMessage(true, 200,obj);
    }
}
