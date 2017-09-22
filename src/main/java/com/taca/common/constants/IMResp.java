package com.taca.common.constants;

/**
 * Created by jetty on 17/9/22.
 */
public enum IMResp {



    SUCCESS("000000","返回成功"),
    DBEXCEPTION("555555","数据库异常"),
    FAIL("999999","系统异常"),
    ;

     private String code;
     private String message;
     IMResp(String code,String message){
        this.code=code;
         this.message=message;
    };

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
}
