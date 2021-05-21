package com.example.common.bean;

public
class RequestProducts {

    /**
     * productId : 1512
     * productNum : 1
     * productName : 衬衫
     * url : http://www.baidu.com
     * productPrice : 20
     */

    private String productId;
    private int productNum;
    private String productName;
    private String url;
    private String productPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
