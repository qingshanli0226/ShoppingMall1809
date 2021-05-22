package com.example.net.bean;

import java.util.List;

public class BagBean {
    /**
     * code : 200
     * msg : 请求成功
     * result : [{"p_catalog_id":"75","parent_id":"0","name":"包包","pic":"","is_deleted":"0","child":[{"p_catalog_id":"76","parent_id":"75","name":"女包","pic":"/product_catalog/1446017977747.jpg","is_deleted":"0"},{"p_catalog_id":"82","parent_id":"75","name":"单肩包","pic":"/product_catalog/1446018101470.jpg","is_deleted":"0"},{"p_catalog_id":"104","parent_id":"75","name":"其他","pic":"/product_catalog/1465384002640.jpg","is_deleted":"0"},{"p_catalog_id":"77","parent_id":"75","name":"钱包","pic":"/product_catalog/1446018023668.jpg","is_deleted":"0"},{"p_catalog_id":"78","parent_id":"75","name":"帆布袋","pic":"/product_catalog/1446018040491.jpg","is_deleted":"0"},{"p_catalog_id":"79","parent_id":"75","name":"背包","pic":"/product_catalog/1446018052794.jpg","is_deleted":"0"},{"p_catalog_id":"97","parent_id":"75","name":"拉箱","pic":"/product_catalog/1449660841905.jpg","is_deleted":"0"}],"hot_product_list":[{"product_id":"2944","channel_id":"4","brand_id":"3","p_catalog_id":"77","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔】剑网3 剑三周边 软面拉链零钱包 女式手拿包包 丐帮酒壶","cover_price":"33.15","brief":"","figure":"/1447999535316.jpg","sell_time_start":"1478772000","sell_time_end":"1448553600"},{"product_id":"7228","channel_id":"8","brand_id":"234","p_catalog_id":"82","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】 原创 日式和风 单肩斜挎包 女式印花背包 A103","cover_price":"40.00","brief":"预售到7月下旬","figure":"/1466154447310.jpg","sell_time_start":"1478772000","sell_time_end":"1466697600"},{"product_id":"2879","channel_id":"8","brand_id":"178","p_catalog_id":"76","supplier_type":"2","supplier_code":"1101003","name":"ISOS原创森系甜美印花单肩斜挎包小方包","cover_price":"59.00","brief":"","figure":"/1447903575122.jpg","sell_time_start":"1447862400","sell_time_end":"1448467200"},{"product_id":"2654","channel_id":"3","brand_id":"191","p_catalog_id":"76","supplier_type":"2","supplier_code":"1101004","name":"【绝对萌域】请问您今天要来点兔子吗 智乃 迷你单肩斜挎小包","cover_price":"43.12","brief":"","figure":"/1450859589972.jpg","sell_time_start":"1478772000","sell_time_end":"1447603200"},{"product_id":"3404","channel_id":"3","brand_id":"69","p_catalog_id":"82","supplier_type":"1","supplier_code":"0","name":"[散漫舍]怪物猎人4 艾露猫 猫爪切片 背包 单肩包 斜跨包","cover_price":"79.00","brief":"本商品由散漫舍提供，5个工作日内发货","figure":"/1449715360061.jpg","sell_time_start":"1449676800","sell_time_end":"1450281600"},{"product_id":"6414","channel_id":"4","brand_id":"3","p_catalog_id":"77","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔】剑网3剑三周边 新宠卡通门派零钱包 小钱包 女士包","cover_price":"15.00","brief":"","figure":"/1469587052018.jpg","sell_time_start":"1462896000","sell_time_end":"1463500800"}]}]
     */

    private int code;
    private String msg;
    /**
     * p_catalog_id : 75
     * parent_id : 0
     * name : 包包
     * pic :
     * is_deleted : 0
     * child : [{"p_catalog_id":"76","parent_id":"75","name":"女包","pic":"/product_catalog/1446017977747.jpg","is_deleted":"0"},{"p_catalog_id":"82","parent_id":"75","name":"单肩包","pic":"/product_catalog/1446018101470.jpg","is_deleted":"0"},{"p_catalog_id":"104","parent_id":"75","name":"其他","pic":"/product_catalog/1465384002640.jpg","is_deleted":"0"},{"p_catalog_id":"77","parent_id":"75","name":"钱包","pic":"/product_catalog/1446018023668.jpg","is_deleted":"0"},{"p_catalog_id":"78","parent_id":"75","name":"帆布袋","pic":"/product_catalog/1446018040491.jpg","is_deleted":"0"},{"p_catalog_id":"79","parent_id":"75","name":"背包","pic":"/product_catalog/1446018052794.jpg","is_deleted":"0"},{"p_catalog_id":"97","parent_id":"75","name":"拉箱","pic":"/product_catalog/1449660841905.jpg","is_deleted":"0"}]
     * hot_product_list : [{"product_id":"2944","channel_id":"4","brand_id":"3","p_catalog_id":"77","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔】剑网3 剑三周边 软面拉链零钱包 女式手拿包包 丐帮酒壶","cover_price":"33.15","brief":"","figure":"/1447999535316.jpg","sell_time_start":"1478772000","sell_time_end":"1448553600"},{"product_id":"7228","channel_id":"8","brand_id":"234","p_catalog_id":"82","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】 原创 日式和风 单肩斜挎包 女式印花背包 A103","cover_price":"40.00","brief":"预售到7月下旬","figure":"/1466154447310.jpg","sell_time_start":"1478772000","sell_time_end":"1466697600"},{"product_id":"2879","channel_id":"8","brand_id":"178","p_catalog_id":"76","supplier_type":"2","supplier_code":"1101003","name":"ISOS原创森系甜美印花单肩斜挎包小方包","cover_price":"59.00","brief":"","figure":"/1447903575122.jpg","sell_time_start":"1447862400","sell_time_end":"1448467200"},{"product_id":"2654","channel_id":"3","brand_id":"191","p_catalog_id":"76","supplier_type":"2","supplier_code":"1101004","name":"【绝对萌域】请问您今天要来点兔子吗 智乃 迷你单肩斜挎小包","cover_price":"43.12","brief":"","figure":"/1450859589972.jpg","sell_time_start":"1478772000","sell_time_end":"1447603200"},{"product_id":"3404","channel_id":"3","brand_id":"69","p_catalog_id":"82","supplier_type":"1","supplier_code":"0","name":"[散漫舍]怪物猎人4 艾露猫 猫爪切片 背包 单肩包 斜跨包","cover_price":"79.00","brief":"本商品由散漫舍提供，5个工作日内发货","figure":"/1449715360061.jpg","sell_time_start":"1449676800","sell_time_end":"1450281600"},{"product_id":"6414","channel_id":"4","brand_id":"3","p_catalog_id":"77","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔】剑网3剑三周边 新宠卡通门派零钱包 小钱包 女士包","cover_price":"15.00","brief":"","figure":"/1469587052018.jpg","sell_time_start":"1462896000","sell_time_end":"1463500800"}]
     */

    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String p_catalog_id;
        private String parent_id;
        private String name;
        private String pic;
        private String is_deleted;
        /**
         * p_catalog_id : 76
         * parent_id : 75
         * name : 女包
         * pic : /product_catalog/1446017977747.jpg
         * is_deleted : 0
         */

        private List<ChildBean> child;
        /**
         * product_id : 2944
         * channel_id : 4
         * brand_id : 3
         * p_catalog_id : 77
         * supplier_type : 1
         * supplier_code : 1101036
         * name : 【小迷兔】剑网3 剑三周边 软面拉链零钱包 女式手拿包包 丐帮酒壶
         * cover_price : 33.15
         * brief :
         * figure : /1447999535316.jpg
         * sell_time_start : 1478772000
         * sell_time_end : 1448553600
         */

        private List<HotProductListBean> hot_product_list;

        public String getP_catalog_id() {
            return p_catalog_id;
        }

        public void setP_catalog_id(String p_catalog_id) {
            this.p_catalog_id = p_catalog_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public List<HotProductListBean> getHot_product_list() {
            return hot_product_list;
        }

        public void setHot_product_list(List<HotProductListBean> hot_product_list) {
            this.hot_product_list = hot_product_list;
        }

        public static class ChildBean {
            private String p_catalog_id;
            private String parent_id;
            private String name;
            private String pic;
            private String is_deleted;

            public String getP_catalog_id() {
                return p_catalog_id;
            }

            public void setP_catalog_id(String p_catalog_id) {
                this.p_catalog_id = p_catalog_id;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(String is_deleted) {
                this.is_deleted = is_deleted;
            }
        }

        public static class HotProductListBean {
            private String product_id;
            private String channel_id;
            private String brand_id;
            private String p_catalog_id;
            private String supplier_type;
            private String supplier_code;
            private String name;
            private String cover_price;
            private String brief;
            private String figure;
            private String sell_time_start;
            private String sell_time_end;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(String channel_id) {
                this.channel_id = channel_id;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getP_catalog_id() {
                return p_catalog_id;
            }

            public void setP_catalog_id(String p_catalog_id) {
                this.p_catalog_id = p_catalog_id;
            }

            public String getSupplier_type() {
                return supplier_type;
            }

            public void setSupplier_type(String supplier_type) {
                this.supplier_type = supplier_type;
            }

            public String getSupplier_code() {
                return supplier_code;
            }

            public void setSupplier_code(String supplier_code) {
                this.supplier_code = supplier_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover_price() {
                return cover_price;
            }

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public String getSell_time_start() {
                return sell_time_start;
            }

            public void setSell_time_start(String sell_time_start) {
                this.sell_time_start = sell_time_start;
            }

            public String getSell_time_end() {
                return sell_time_end;
            }

            public void setSell_time_end(String sell_time_end) {
                this.sell_time_end = sell_time_end;
            }
        }
    }
}
