package com.example.common.bean;

public class SelectOrderBean {

    private String url;
    private String name;
    private String money;
    private String num;

    @Override
    public String toString() {
        return "SelectOrderBean{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public SelectOrderBean(String url, String name, String money, String num) {
        this.url = url;
        this.name = name;
        this.money = money;
        this.num = num;
    }
}
