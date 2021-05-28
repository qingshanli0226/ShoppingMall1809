package com.example.net.bean;

import java.io.Serializable;
import java.util.List;

public class PayBean implements Serializable {
    /**
     * subject : buy
     * totalPrice : 500
     * body : [{"productName":"jacket","productId":"1000"},{"productName":"jacket","productId":"1001"},{"productName":"jacket","productId":"1002"},{"productName":"jacket","productId":"1003"},{"productName":"jacket","productId":"1004"}]
     */

    private String subject;
    private String totalPrice;
    /**
     * productName : jacket
     * productId : 1000
     */

    private List<BodyBean> body;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean implements Serializable {
        private String productName;
        private String productId;
        private String productNum;
        private String productUrl;
        private String productPrice;

        public BodyBean(String productName, String productId, String productNum, String productUrl, String productPrice) {
            this.productName = productName;
            this.productId = productId;
            this.productNum = productNum;
            this.productUrl = productUrl;
            this.productPrice = productPrice;
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "productName='" + productName + '\'' +
                    ", productId='" + productId + '\'' +
                    ", productNum='" + productNum + '\'' +
                    ", productUrl='" + productUrl + '\'' +
                    ", productPrice='" + productPrice + '\'' +
                    '}';
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductUrl() {
            return productUrl;
        }

        public void setProductUrl(String productUrl) {
            this.productUrl = productUrl;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public BodyBean() {
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }
}
