package com.example.net.bean.store;

public class GoodAdapterBean {
    private String id;
    private String name;
    private String figure;
    private String price;

    public GoodAdapterBean(String id,String name, String figure, String price) {
        this.id = id;
        this.name = name;
        this.figure = figure;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
