package com.example.common.bean;

import java.util.List;

public
class KindGameBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"child":[{"is_deleted":"0","name":"AR专区","p_catalog_id":"42","parent_id":"8","pic":"/product_catalog/1446017464778.jpg"}],"hot_product_list":[{"brand_id":"72","brief":"","channel_id":"3","cover_price":"15.00","figure":"/1448549924430.jpg","name":"【官方正版】全职高手 叶修 苏沐橙 尚硅谷 AR卡","p_catalog_id":"42","product_id":"3041","sell_time_end":"1449072000","sell_time_start":"1448467200","supplier_code":"0","supplier_type":"1"},{"brand_id":"8","brief":"","channel_id":"4","cover_price":"12.00","figure":"/1448610769178.jpg","name":"尚硅谷 AR卡AR同人卡 刀剑乱舞 三日月宗近 小狐丸2.5次元","p_catalog_id":"42","product_id":"3056","sell_time_end":"1449158400","sell_time_start":"1448553600","supplier_code":"0","supplier_type":"1"},{"brand_id":"5","brief":"","channel_id":"8","cover_price":"69.00","figure":"/1464776087422.jpg","name":"【尚硅谷出品】一发入魂 BUFFT恤 ART恤 2.5次元","p_catalog_id":"42","product_id":"6805","sell_time_end":"1465315200","sell_time_start":"1464710400","supplier_code":"0","supplier_type":"1"},{"brand_id":"42","brief":"","channel_id":"3","cover_price":"12.00","figure":"/1448613918568.jpg","name":"尚硅谷 AR卡AR同人卡 黑执事系列动漫 漫画 塞巴斯蒂安 夏尔","p_catalog_id":"42","product_id":"3067","sell_time_end":"1449158400","sell_time_start":"1448553600","supplier_code":"0","supplier_type":"1"},{"brand_id":"86","brief":"","channel_id":"4","cover_price":"12.00","figure":"/1448615420723.jpg","name":"尚硅谷 AR卡AR同人卡 K系列 伊佐那社 夜刀神狗朗 伏见美咲 2.5次元","p_catalog_id":"42","product_id":"3078","sell_time_end":"1449158400","sell_time_start":"1448553600","supplier_code":"0","supplier_type":"1"},{"brand_id":"20","brief":"此宝贝需结合2.5次元应用使用 下载地址：http://www.artongren.com/download/","channel_id":"4","cover_price":"48.00","figure":"/1432113201786.jpg","name":"尚硅谷 AR卡AR同人卡 vocaloid/V家 初音未来 系列全套装 全套4款","p_catalog_id":"42","product_id":"56","sell_time_end":"0","sell_time_start":"0","supplier_code":"0","supplier_type":"1"}],"is_deleted":"0","name":"游戏专区","p_catalog_id":"8","parent_id":"0","pic":""}]
     */

    private int code;
    private String msg;
    /**
     * child : [{"is_deleted":"0","name":"AR专区","p_catalog_id":"42","parent_id":"8","pic":"/product_catalog/1446017464778.jpg"}]
     * hot_product_list : [{"brand_id":"72","brief":"","channel_id":"3","cover_price":"15.00","figure":"/1448549924430.jpg","name":"【官方正版】全职高手 叶修 苏沐橙 尚硅谷 AR卡","p_catalog_id":"42","product_id":"3041","sell_time_end":"1449072000","sell_time_start":"1448467200","supplier_code":"0","supplier_type":"1"},{"brand_id":"8","brief":"","channel_id":"4","cover_price":"12.00","figure":"/1448610769178.jpg","name":"尚硅谷 AR卡AR同人卡 刀剑乱舞 三日月宗近 小狐丸2.5次元","p_catalog_id":"42","product_id":"3056","sell_time_end":"1449158400","sell_time_start":"1448553600","supplier_code":"0","supplier_type":"1"},{"brand_id":"5","brief":"","channel_id":"8","cover_price":"69.00","figure":"/1464776087422.jpg","name":"【尚硅谷出品】一发入魂 BUFFT恤 ART恤 2.5次元","p_catalog_id":"42","product_id":"6805","sell_time_end":"1465315200","sell_time_start":"1464710400","supplier_code":"0","supplier_type":"1"},{"brand_id":"42","brief":"","channel_id":"3","cover_price":"12.00","figure":"/1448613918568.jpg","name":"尚硅谷 AR卡AR同人卡 黑执事系列动漫 漫画 塞巴斯蒂安 夏尔","p_catalog_id":"42","product_id":"3067","sell_time_end":"1449158400","sell_time_start":"1448553600","supplier_code":"0","supplier_type":"1"},{"brand_id":"86","brief":"","channel_id":"4","cover_price":"12.00","figure":"/1448615420723.jpg","name":"尚硅谷 AR卡AR同人卡 K系列 伊佐那社 夜刀神狗朗 伏见美咲 2.5次元","p_catalog_id":"42","product_id":"3078","sell_time_end":"1449158400","sell_time_start":"1448553600","supplier_code":"0","supplier_type":"1"},{"brand_id":"20","brief":"此宝贝需结合2.5次元应用使用 下载地址：http://www.artongren.com/download/","channel_id":"4","cover_price":"48.00","figure":"/1432113201786.jpg","name":"尚硅谷 AR卡AR同人卡 vocaloid/V家 初音未来 系列全套装 全套4款","p_catalog_id":"42","product_id":"56","sell_time_end":"0","sell_time_start":"0","supplier_code":"0","supplier_type":"1"}]
     * is_deleted : 0
     * name : 游戏专区
     * p_catalog_id : 8
     * parent_id : 0
     * pic :
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
        private String is_deleted;
        private String name;
        private String p_catalog_id;
        private String parent_id;
        private String pic;
        /**
         * is_deleted : 0
         * name : AR专区
         * p_catalog_id : 42
         * parent_id : 8
         * pic : /product_catalog/1446017464778.jpg
         */

        private List<ChildBean> child;
        /**
         * brand_id : 72
         * brief :
         * channel_id : 3
         * cover_price : 15.00
         * figure : /1448549924430.jpg
         * name : 【官方正版】全职高手 叶修 苏沐橙 尚硅谷 AR卡
         * p_catalog_id : 42
         * product_id : 3041
         * sell_time_end : 1449072000
         * sell_time_start : 1448467200
         * supplier_code : 0
         * supplier_type : 1
         */

        private List<HotProductListBean> hot_product_list;

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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
            private String is_deleted;
            private String name;
            private String p_catalog_id;
            private String parent_id;
            private String pic;

            public String getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(String is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class HotProductListBean {
            private String brand_id;
            private String brief;
            private String channel_id;
            private String cover_price;
            private String figure;
            private String name;
            private String p_catalog_id;
            private String product_id;
            private String sell_time_end;
            private String sell_time_start;
            private String supplier_code;
            private String supplier_type;

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(String channel_id) {
                this.channel_id = channel_id;
            }

            public String getCover_price() {
                return cover_price;
            }

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getP_catalog_id() {
                return p_catalog_id;
            }

            public void setP_catalog_id(String p_catalog_id) {
                this.p_catalog_id = p_catalog_id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getSell_time_end() {
                return sell_time_end;
            }

            public void setSell_time_end(String sell_time_end) {
                this.sell_time_end = sell_time_end;
            }

            public String getSell_time_start() {
                return sell_time_start;
            }

            public void setSell_time_start(String sell_time_start) {
                this.sell_time_start = sell_time_start;
            }

            public String getSupplier_code() {
                return supplier_code;
            }

            public void setSupplier_code(String supplier_code) {
                this.supplier_code = supplier_code;
            }

            public String getSupplier_type() {
                return supplier_type;
            }

            public void setSupplier_type(String supplier_type) {
                this.supplier_type = supplier_type;
            }
        }
    }
}
