package com.example.net.bean;

import java.util.List;

public class JacketBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"p_catalog_id":"51","parent_id":"0","name":"上衣","pic":"","is_deleted":"0","child":[{"p_catalog_id":"52","parent_id":"51","name":"日常","pic":"/product_catalog/1446017524434.jpg","is_deleted":"0"},{"p_catalog_id":"96","parent_id":"51","name":"泳衣","pic":"/product_catalog/1449216802403.jpg","is_deleted":"0"},{"p_catalog_id":"109","parent_id":"51","name":"汉风","pic":"/product_catalog/1465383063303.jpg","is_deleted":"0"},{"p_catalog_id":"117","parent_id":"51","name":"古风","pic":"","is_deleted":"0"},{"p_catalog_id":"55","parent_id":"51","name":"lolita","pic":"/product_catalog/1446017570261.jpg","is_deleted":"0"},{"p_catalog_id":"56","parent_id":"51","name":"创意T恤","pic":"/product_catalog/1446017583897.jpg","is_deleted":"0"}],"hot_product_list":[{"product_id":"627","channel_id":"4","brand_id":"3","p_catalog_id":"52","supplier_type":"1","supplier_code":"1101001","name":"剑三T恤批发","cover_price":"32.00","brief":"","figure":"/1439367400560.jpg","sell_time_start":"1439308800","sell_time_end":"1439913600"},{"product_id":"6895","channel_id":"15","brand_id":"402","p_catalog_id":"52","supplier_type":"2","supplier_code":"802004","name":"【流烟昔泠】汉元素 半臂 短宋裤 吊带 刺绣 豆蔻年华少女系列-清秋兔 半臂","cover_price":"99.00","brief":"7月15日起进入第四批预定，第四批约7月31日左右发货","figure":"/1465295954097.jpg","sell_time_start":"1465228800","sell_time_end":"1465833600"},{"product_id":"6896","channel_id":"15","brand_id":"402","p_catalog_id":"52","supplier_type":"2","supplier_code":"802004","name":"【流烟昔泠】汉元素 半臂 短宋裤 吊带 刺绣 豆蔻年华少女系列-清秋兔 吊带","cover_price":"59.00","brief":"7月15日起进入第四批预定，第四批约7月31日左右发货","figure":"/1465296158907.jpg","sell_time_start":"1465228800","sell_time_end":"1465833600"},{"product_id":"4628","channel_id":"8","brand_id":"5","p_catalog_id":"56","supplier_type":"2","supplier_code":"1201001","name":"【漫踪】 原创可爱萌猫咪大人 立体猫尾T恤 蕾丝花边女","cover_price":"79.00","brief":"","figure":"/1461571069605.jpg","sell_time_start":"1455638400","sell_time_end":"1456243200"},{"product_id":"3691","channel_id":"8","brand_id":"90","p_catalog_id":"52","supplier_type":"2","supplier_code":"1201001","name":"【漫踪】宫崎骏 龙猫套装 女冬 帽子衬衫背心打底裤短裤","cover_price":"105.00","brief":"背心上小挂件赠完即止哦 ","figure":"/1451012249243.jpg","sell_time_start":"1450972800","sell_time_end":"1451577600"},{"product_id":"6263","channel_id":"8","brand_id":"234","p_catalog_id":"56","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】原创设计 恶灵退散纯棉七分袖T恤 秋季日系原宿男女装A21","cover_price":"63.00","brief":"","figure":"/1464245798979.jpg","sell_time_start":"1478772000","sell_time_end":"1462982400"}]}]
     */

    private int code;
    private String msg;
    /**
     * p_catalog_id : 51
     * parent_id : 0
     * name : 上衣
     * pic :
     * is_deleted : 0
     * child : [{"p_catalog_id":"52","parent_id":"51","name":"日常","pic":"/product_catalog/1446017524434.jpg","is_deleted":"0"},{"p_catalog_id":"96","parent_id":"51","name":"泳衣","pic":"/product_catalog/1449216802403.jpg","is_deleted":"0"},{"p_catalog_id":"109","parent_id":"51","name":"汉风","pic":"/product_catalog/1465383063303.jpg","is_deleted":"0"},{"p_catalog_id":"117","parent_id":"51","name":"古风","pic":"","is_deleted":"0"},{"p_catalog_id":"55","parent_id":"51","name":"lolita","pic":"/product_catalog/1446017570261.jpg","is_deleted":"0"},{"p_catalog_id":"56","parent_id":"51","name":"创意T恤","pic":"/product_catalog/1446017583897.jpg","is_deleted":"0"}]
     * hot_product_list : [{"product_id":"627","channel_id":"4","brand_id":"3","p_catalog_id":"52","supplier_type":"1","supplier_code":"1101001","name":"剑三T恤批发","cover_price":"32.00","brief":"","figure":"/1439367400560.jpg","sell_time_start":"1439308800","sell_time_end":"1439913600"},{"product_id":"6895","channel_id":"15","brand_id":"402","p_catalog_id":"52","supplier_type":"2","supplier_code":"802004","name":"【流烟昔泠】汉元素 半臂 短宋裤 吊带 刺绣 豆蔻年华少女系列-清秋兔 半臂","cover_price":"99.00","brief":"7月15日起进入第四批预定，第四批约7月31日左右发货","figure":"/1465295954097.jpg","sell_time_start":"1465228800","sell_time_end":"1465833600"},{"product_id":"6896","channel_id":"15","brand_id":"402","p_catalog_id":"52","supplier_type":"2","supplier_code":"802004","name":"【流烟昔泠】汉元素 半臂 短宋裤 吊带 刺绣 豆蔻年华少女系列-清秋兔 吊带","cover_price":"59.00","brief":"7月15日起进入第四批预定，第四批约7月31日左右发货","figure":"/1465296158907.jpg","sell_time_start":"1465228800","sell_time_end":"1465833600"},{"product_id":"4628","channel_id":"8","brand_id":"5","p_catalog_id":"56","supplier_type":"2","supplier_code":"1201001","name":"【漫踪】 原创可爱萌猫咪大人 立体猫尾T恤 蕾丝花边女","cover_price":"79.00","brief":"","figure":"/1461571069605.jpg","sell_time_start":"1455638400","sell_time_end":"1456243200"},{"product_id":"3691","channel_id":"8","brand_id":"90","p_catalog_id":"52","supplier_type":"2","supplier_code":"1201001","name":"【漫踪】宫崎骏 龙猫套装 女冬 帽子衬衫背心打底裤短裤","cover_price":"105.00","brief":"背心上小挂件赠完即止哦 ","figure":"/1451012249243.jpg","sell_time_start":"1450972800","sell_time_end":"1451577600"},{"product_id":"6263","channel_id":"8","brand_id":"234","p_catalog_id":"56","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】原创设计 恶灵退散纯棉七分袖T恤 秋季日系原宿男女装A21","cover_price":"63.00","brief":"","figure":"/1464245798979.jpg","sell_time_start":"1478772000","sell_time_end":"1462982400"}]
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
         * p_catalog_id : 52
         * parent_id : 51
         * name : 日常
         * pic : /product_catalog/1446017524434.jpg
         * is_deleted : 0
         */

        private List<ChildBean> child;
        /**
         * product_id : 627
         * channel_id : 4
         * brand_id : 3
         * p_catalog_id : 52
         * supplier_type : 1
         * supplier_code : 1101001
         * name : 剑三T恤批发
         * cover_price : 32.00
         * brief :
         * figure : /1439367400560.jpg
         * sell_time_start : 1439308800
         * sell_time_end : 1439913600
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
