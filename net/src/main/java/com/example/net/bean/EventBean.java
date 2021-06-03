package com.example.net.bean;

public class EventBean {
    private int type;
    private int flag;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventBean() {
    }

    public EventBean(int type, int flag, String message) {
        this.type = type;
        this.flag = flag;
        this.message = message;
    }
}
