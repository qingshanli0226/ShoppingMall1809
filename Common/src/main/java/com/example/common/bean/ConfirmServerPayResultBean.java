package com.example.common.bean;

public class ConfirmServerPayResultBean {

    /**
     * code : 200
     * message : 请求成功
     * result : true
     */

    private String code;
    private String message;
    private boolean result;

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

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
