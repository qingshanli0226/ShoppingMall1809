package com.example.net.model;

import java.util.List;

public class OrderInfoParamBean {

    /**
     * subject : buy
     * totalPrice : 500
     * body : [{"productName":"jacket","productId":"1000"},{"productName":"jacket","productId":"1001"},{"productName":"jacket","productId":"1002"},{"productName":"jacket","productId":"1003"},{"productName":"jacket","productId":"1004"}]
     */

    private String subject;
    private String totalPrice;
    private List<BodyBean> body;

    public OrderInfoParamBean(String subject, String totalPrice, List<BodyBean> body) {
        this.subject = subject;
        this.totalPrice = totalPrice;
        this.body = body;
    }

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

    public static class BodyBean {
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

        public BodyBean(String productName, String productId) {
            this.productName = productName;
            this.productId = productId;
        }

        /**
         * productName : jacket
         * productId : 1000
         */




        private String productName;
        private String productId;
    }
}
