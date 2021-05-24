package com.example.net.bean.store;

public class GoodAdapterBean {
    private String name;
    private String figure;
    private String price;

    public GoodAdapterBean(String name, String figure, String price) {
        this.name = name;
        this.figure = figure;
        this.price = price;
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
