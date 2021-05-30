package com.example.net.bean;

import java.util.List;

public class CheckNumAll {
    /**
     * code : 200
     * message : 请求成功
     * result : [{"productId":"1000","productName":null,"productNum":"0","url":null,"productPrice":null},{"productId":"1001","productName":null,"productNum":"1","url":null,"productPrice":null},{"productId":"1002","productName":null,"productNum":"2","url":null,"productPrice":null},{"productId":"1003","productName":null,"productNum":"2","url":null,"productPrice":null},{"productId":"1004","productName":null,"productNum":"2","url":null,"productPrice":null}]
     */

    private String code;
    private String message;
    /**
     * productId : 1000
     * productName : null
     * productNum : 0
     * url : null
     * productPrice : null
     */

    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String productId;
        private Object productName;
        private String productNum;
        private Object url;
        private Object productPrice;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Object getProductName() {
            return productName;
        }

        public void setProductName(Object productName) {
            this.productName = productName;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public Object getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(Object productPrice) {
            this.productPrice = productPrice;
        }
    }
}
