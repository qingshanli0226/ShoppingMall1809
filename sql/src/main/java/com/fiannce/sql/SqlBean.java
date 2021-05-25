package com.fiannce.sql;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SqlBean {
    private String productId;
    private int productNum;
    private String productName;
    private String url;
    private String productPrice;
    private boolean productSelected;
    @Generated(hash = 1321301547)
    public SqlBean(String productId, int productNum, String productName, String url,
            String productPrice, boolean productSelected) {
        this.productId = productId;
        this.productNum = productNum;
        this.productName = productName;
        this.url = url;
        this.productPrice = productPrice;
        this.productSelected = productSelected;
    }
    @Generated(hash = 2066760633)
    public SqlBean() {
    }
    public String getProductId() {
        return this.productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public int getProductNum() {
        return this.productNum;
    }
    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getProductPrice() {
        return this.productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
    public boolean getProductSelected() {
        return this.productSelected;
    }
    public void setProductSelected(boolean productSelected) {
        this.productSelected = productSelected;
    }
}
