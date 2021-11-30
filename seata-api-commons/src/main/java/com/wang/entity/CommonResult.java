package com.wang.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * @author username
 * @date 2021/11/28 11:13
 * @description:TODO
 * @since 8
 */
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1305970441828039617L;
    //状态码
    private Integer code;
    //消息
    private String message;
    //泛型
    private T data;
    public CommonResult() {
    }
    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static CommonResult<Object> build (Integer code, String message, Object data){
        return new CommonResult<>(code,message,data);
    }

    /**
     *
     * @param message 传递的消息
     * @return 返回commonResult
     */
    public static CommonResult<Object> successResult(String message){
        return new CommonResult<>(200,message,null);
    }

    /**
     *
     * @param message 传递的消息
     * @param data 传递的数据
     * @return 返回commonResult
     */
    public static CommonResult<Object> successResult(String message , Object data){
        return new CommonResult<>(200,message,data);
    }

    /**
     *
     * @param message 传递的消息
     * @return 返回commonResult
     */
    public static CommonResult<Object> errorResult(String message){
        return new CommonResult<>(500,message,null);
    }

    /**
     *
     * @param message 传递的消息
     * @param data 传递的map数据
     * @return 返回commonResult
     */
    public static CommonResult<Map<String,Object>> errorResult(String message , Map<String,Object> data){
        return new CommonResult<>(501,message,data);
    }

    /**
     *
     * @param message 传递的消息
     * @return 返回commonResult
     * @param data 传递的数据
     */
    public static CommonResult<Object> errorResult(String message , Object data){
        return new CommonResult<>(502,message,data);
    }

    /**
     *
     * @param message 传递的消息
     * @return 返回commonResult
     */
    public static CommonResult<Object> errorTokenResult(String message){
        return new CommonResult<>(503,message,null);
    }

    /**
     *
     * @param message 传递的消息
     * @return 返回commonResult
     */
    public static CommonResult<Object> errorExceptionResult(String message){
        return new CommonResult<>(555,message,null);
    }

    /**
     *
     * @param message 传递的消息
     * @param data 传递的数据
     * @return 返回commonResult
     */
    public static CommonResult<Object> errorExceptionResult(String message , Object data){
        return new CommonResult<>(555,message,data);
    }


}