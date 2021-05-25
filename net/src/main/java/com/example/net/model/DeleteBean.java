package com.example.net.model;

public class DeleteBean {
    private String productId;
    private String productNum;
    private String productName;
    private String url;

    public DeleteBean(String productId, String productNum, String productName, String url) {
        this.productId = productId;
        this.productNum = productNum;
        this.productName = productName;
        this.url = url;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
