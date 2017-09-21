package com.taca.common.bean;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * Created by jetty on 17/9/21.
 */
public class ResultBean<T> implements Serializable {

    public static final String SUCCESS="000000";
    public static final String FAIL="111111";
    private T data;
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean(){
    }


    public ResultBean(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
