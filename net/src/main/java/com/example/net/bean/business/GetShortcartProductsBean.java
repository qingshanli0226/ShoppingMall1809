package com.example.net.bean.business;

import java.util.List;

public class GetShortcartProductsBean {

    /**
     * code : 200
     * message : 请求成功
     * result : [{"productId":"9356","productName":"现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪","productNum":"1","url":"/1477984921265.jpg","productPrice":null,"productSelected":false},{"productId":"10391","productName":"现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪-特典版","productNum":"1","url":"/1477984931882.jpg","productPrice":null,"productSelected":false},{"productId":"10136","productName":"【高冷猫】暗黑系软妹病娇药丸少女秋装假俩件加厚卫衣帽衫  预售","productNum":"1","url":"/1477360350123.png","productPrice":null,"productSelected":false},{"productId":"6869","productName":"【艾漫】全职高手-蜜饯系列","productNum":"1","url":"/1465268743242.jpg","productPrice":null,"productSelected":false},{"productId":"7752","productName":"【wacom】数位板画板ctl471手绘板bamboo电脑绘画电子绘图板ps","productNum":"1","url":"/supplier/1467702094592.jpg","productPrice":null,"productSelected":false}]
     */

    private String code;
    private String message;
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
        /**
         * productId : 9356
         * productName : 现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪
         * productNum : 1
         * url : /1477984921265.jpg
         * productPrice : null
         * productSelected : false
         */

        private String productId;
        private String productName;
        private String productNum;
        private String url;
        private Object productPrice;
        private boolean productSelected;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(Object productPrice) {
            this.productPrice = productPrice;
        }

        public boolean isProductSelected() {
            return productSelected;
        }

        public void setProductSelected(boolean productSelected) {
            this.productSelected = productSelected;
        }
    }
}
