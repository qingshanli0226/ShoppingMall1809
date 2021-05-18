package com.example.common.bean;

import java.util.List;

public
class KindDigitBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"p_catalog_id":"7","parent_id":"0","name":"数码周边","pic":"","is_deleted":"0","child":[{"p_catalog_id":"119","parent_id":"7","name":"自拍杆","pic":"","is_deleted":"0"},{"p_catalog_id":"118","parent_id":"7","name":"屏幕保护膜","pic":"","is_deleted":"0"},{"p_catalog_id":"116","parent_id":"7","name":"Wacom系列","pic":"/1472112184129.jpg","is_deleted":"0"},{"p_catalog_id":"112","parent_id":"7","name":"交通卡","pic":"/product_catalog/1465808719970.jpg","is_deleted":"0"},{"p_catalog_id":"103","parent_id":"7","name":"鼠键","pic":"/product_catalog/1454061759825.jpg","is_deleted":"0"},{"p_catalog_id":"102","parent_id":"7","name":"U盘","pic":"/product_catalog/1454061108683.jpg","is_deleted":"0"},{"p_catalog_id":"101","parent_id":"7","name":"耳机","pic":"/product_catalog/1454060084922.png","is_deleted":"0"},{"p_catalog_id":"93","parent_id":"7","name":"票务","pic":"/product_catalog/1454484273989.jpg","is_deleted":"0"},{"p_catalog_id":"50","parent_id":"7","name":"手机壳","pic":"/product_catalog/1446017444813.jpg","is_deleted":"0"},{"p_catalog_id":"39","parent_id":"7","name":"移动电源","pic":"/product_catalog/1446017382141.jpg","is_deleted":"0"},{"p_catalog_id":"40","parent_id":"7","name":"鼠标垫","pic":"/product_catalog/1446017408013.jpg","is_deleted":"0"},{"p_catalog_id":"41","parent_id":"7","name":"金属贴","pic":"/product_catalog/1446017422349.jpg","is_deleted":"0"}],"hot_product_list":[{"product_id":"8262","channel_id":"3","brand_id":"5","p_catalog_id":"101","supplier_type":"2","supplier_code":"1102013","name":"Censi 声氏 Moecen猫耳耳机 二次元 动漫 卖萌 头戴式 蓝牙耳机【白色版】","cover_price":"299.00","brief":"","figure":"/1469173640233.jpg","sell_time_start":"1469116800","sell_time_end":"1469721600"},{"product_id":"7752","channel_id":"3","brand_id":"421","p_catalog_id":"116","supplier_type":"2","supplier_code":"400003","name":"【wacom】数位板画板ctl471手绘板bamboo电脑绘画电子绘图板ps","cover_price":"329.00","brief":"WACOM CTL471","figure":"/supplier/1467702094592.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"2361","channel_id":"3","brand_id":"5","p_catalog_id":"7","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】 冬季暖暖 和风手机被套 仓鼠被窝","cover_price":"15.00","brief":"","figure":"/1445418450175.jpg","sell_time_start":"1478772000","sell_time_end":"1445961600"},{"product_id":"8120","channel_id":"4","brand_id":"274","p_catalog_id":"41","supplier_type":"2","supplier_code":"802001","name":"【砚池工作室】剑网三 剑3同人全门派衍生logo周边 金属手机贴【银色】","cover_price":"30.00","brief":"","figure":"/1468840600394.jpg","sell_time_start":"1468771200","sell_time_end":"1469376000"},{"product_id":"10652","channel_id":"3","brand_id":"5","p_catalog_id":"101","supplier_type":"2","supplier_code":"2101002","name":"【歪瓜出品】Spirit E666精灵耳朵 入耳式耳机 cos摄影道具直播耳塞","cover_price":"99.00","brief":"","figure":"/1478833408658.jpg","sell_time_start":"1478793600","sell_time_end":"1479398400"},{"product_id":"9709","channel_id":"3","brand_id":"305","p_catalog_id":"119","supplier_type":"1","supplier_code":"1102019","name":"【ROCK】哆啦A梦自拍杆神器","cover_price":"49.00","brief":"","figure":"/1475979038217.jpg","sell_time_start":"1478581200","sell_time_end":"1478581200"}]}]
     */

    private int code;
    private String msg;
    /**
     * p_catalog_id : 7
     * parent_id : 0
     * name : 数码周边
     * pic :
     * is_deleted : 0
     * child : [{"p_catalog_id":"119","parent_id":"7","name":"自拍杆","pic":"","is_deleted":"0"},{"p_catalog_id":"118","parent_id":"7","name":"屏幕保护膜","pic":"","is_deleted":"0"},{"p_catalog_id":"116","parent_id":"7","name":"Wacom系列","pic":"/1472112184129.jpg","is_deleted":"0"},{"p_catalog_id":"112","parent_id":"7","name":"交通卡","pic":"/product_catalog/1465808719970.jpg","is_deleted":"0"},{"p_catalog_id":"103","parent_id":"7","name":"鼠键","pic":"/product_catalog/1454061759825.jpg","is_deleted":"0"},{"p_catalog_id":"102","parent_id":"7","name":"U盘","pic":"/product_catalog/1454061108683.jpg","is_deleted":"0"},{"p_catalog_id":"101","parent_id":"7","name":"耳机","pic":"/product_catalog/1454060084922.png","is_deleted":"0"},{"p_catalog_id":"93","parent_id":"7","name":"票务","pic":"/product_catalog/1454484273989.jpg","is_deleted":"0"},{"p_catalog_id":"50","parent_id":"7","name":"手机壳","pic":"/product_catalog/1446017444813.jpg","is_deleted":"0"},{"p_catalog_id":"39","parent_id":"7","name":"移动电源","pic":"/product_catalog/1446017382141.jpg","is_deleted":"0"},{"p_catalog_id":"40","parent_id":"7","name":"鼠标垫","pic":"/product_catalog/1446017408013.jpg","is_deleted":"0"},{"p_catalog_id":"41","parent_id":"7","name":"金属贴","pic":"/product_catalog/1446017422349.jpg","is_deleted":"0"}]
     * hot_product_list : [{"product_id":"8262","channel_id":"3","brand_id":"5","p_catalog_id":"101","supplier_type":"2","supplier_code":"1102013","name":"Censi 声氏 Moecen猫耳耳机 二次元 动漫 卖萌 头戴式 蓝牙耳机【白色版】","cover_price":"299.00","brief":"","figure":"/1469173640233.jpg","sell_time_start":"1469116800","sell_time_end":"1469721600"},{"product_id":"7752","channel_id":"3","brand_id":"421","p_catalog_id":"116","supplier_type":"2","supplier_code":"400003","name":"【wacom】数位板画板ctl471手绘板bamboo电脑绘画电子绘图板ps","cover_price":"329.00","brief":"WACOM CTL471","figure":"/supplier/1467702094592.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"2361","channel_id":"3","brand_id":"5","p_catalog_id":"7","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】 冬季暖暖 和风手机被套 仓鼠被窝","cover_price":"15.00","brief":"","figure":"/1445418450175.jpg","sell_time_start":"1478772000","sell_time_end":"1445961600"},{"product_id":"8120","channel_id":"4","brand_id":"274","p_catalog_id":"41","supplier_type":"2","supplier_code":"802001","name":"【砚池工作室】剑网三 剑3同人全门派衍生logo周边 金属手机贴【银色】","cover_price":"30.00","brief":"","figure":"/1468840600394.jpg","sell_time_start":"1468771200","sell_time_end":"1469376000"},{"product_id":"10652","channel_id":"3","brand_id":"5","p_catalog_id":"101","supplier_type":"2","supplier_code":"2101002","name":"【歪瓜出品】Spirit E666精灵耳朵 入耳式耳机 cos摄影道具直播耳塞","cover_price":"99.00","brief":"","figure":"/1478833408658.jpg","sell_time_start":"1478793600","sell_time_end":"1479398400"},{"product_id":"9709","channel_id":"3","brand_id":"305","p_catalog_id":"119","supplier_type":"1","supplier_code":"1102019","name":"【ROCK】哆啦A梦自拍杆神器","cover_price":"49.00","brief":"","figure":"/1475979038217.jpg","sell_time_start":"1478581200","sell_time_end":"1478581200"}]
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
         * p_catalog_id : 119
         * parent_id : 7
         * name : 自拍杆
         * pic :
         * is_deleted : 0
         */

        private List<ChildBean> child;
        /**
         * product_id : 8262
         * channel_id : 3
         * brand_id : 5
         * p_catalog_id : 101
         * supplier_type : 2
         * supplier_code : 1102013
         * name : Censi 声氏 Moecen猫耳耳机 二次元 动漫 卖萌 头戴式 蓝牙耳机【白色版】
         * cover_price : 299.00
         * brief :
         * figure : /1469173640233.jpg
         * sell_time_start : 1469116800
         * sell_time_end : 1469721600
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
