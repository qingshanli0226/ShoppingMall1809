package com.example.common.bean;

public class OrderBean {
    /**
     * productId : 1000
     * productNum : 0
     * productName : 衬衫
     * url : http://www.baidu.com
     */

    private String productId;
    private String productNum;
    private String productName;
    private String url;

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
