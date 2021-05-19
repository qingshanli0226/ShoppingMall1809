package com.example.net.bean;

import java.util.List;

public class ProductsBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"child":[{"is_deleted":"0","name":"优惠券","p_catalog_id":"115","parent_id":"5","pic":"/product_catalog/1469183837648.png"},{"is_deleted":"0","name":"家纺品","p_catalog_id":"81","parent_id":"5","pic":"/product_catalog/1446017029488.jpg"},{"is_deleted":"0","name":"眼镜布","p_catalog_id":"85","parent_id":"5","pic":"/product_catalog/1446017110058.jpg"},{"is_deleted":"0","name":"毛巾","p_catalog_id":"86","parent_id":"5","pic":"/product_catalog/1446017158779.jpg"},{"is_deleted":"0","name":"创意宅物","p_catalog_id":"90","parent_id":"5","pic":"/product_catalog/1446017175586.jpg"},{"is_deleted":"0","name":"零食","p_catalog_id":"99","parent_id":"5","pic":"/product_catalog/1449828955995.png"},{"is_deleted":"0","name":"节日特典","p_catalog_id":"100","parent_id":"5","pic":"/product_catalog/1450682473783.png"},{"is_deleted":"0","name":"桌游","p_catalog_id":"106","parent_id":"5","pic":"/product_catalog/1461814801527.jpg"},{"is_deleted":"0","name":"兵人","p_catalog_id":"107","parent_id":"5","pic":"/product_catalog/1465383093721.jpg"},{"is_deleted":"0","name":"BJD","p_catalog_id":"113","parent_id":"5","pic":"/product_catalog/1468306500168.jpg"},{"is_deleted":"0","name":"手办","p_catalog_id":"48","parent_id":"5","pic":"/product_catalog/1446017012089.jpg"},{"is_deleted":"0","name":"明信片","p_catalog_id":"23","parent_id":"5","pic":"/product_catalog/1446016836847.jpg"},{"is_deleted":"0","name":"书籍","p_catalog_id":"24","parent_id":"5","pic":"/product_catalog/1446016851701.jpg"},{"is_deleted":"0","name":"扭蛋/蛋盒","p_catalog_id":"25","parent_id":"5","pic":"/product_catalog/1446016874422.jpg"},{"is_deleted":"0","name":"挂件","p_catalog_id":"26","parent_id":"5","pic":"/product_catalog/1446016891091.jpg"},{"is_deleted":"0","name":"挂画海报","p_catalog_id":"27","parent_id":"5","pic":"/product_catalog/1446016906565.jpg"},{"is_deleted":"0","name":"餐具","p_catalog_id":"28","parent_id":"5","pic":"/product_catalog/1446016944877.jpg"},{"is_deleted":"0","name":"公仔","p_catalog_id":"29","parent_id":"5","pic":"/product_catalog/1446016961418.jpg"},{"is_deleted":"0","name":"雨伞","p_catalog_id":"17","parent_id":"5","pic":"/product_catalog/1446016762275.jpg"},{"is_deleted":"0","name":"DIY","p_catalog_id":"30","parent_id":"5","pic":"/product_catalog/1446016974517.jpg"},{"is_deleted":"0","name":"扇子","p_catalog_id":"31","parent_id":"5","pic":"/product_catalog/1446016989168.jpg"},{"is_deleted":"0","name":"抱枕","p_catalog_id":"22","parent_id":"5","pic":"/product_catalog/1446016784975.jpg"},{"is_deleted":"0","name":"等身抱枕","p_catalog_id":"83","parent_id":"5","pic":"/product_catalog/1446017074190.jpg"},{"is_deleted":"0","name":"护肤品","p_catalog_id":"98","parent_id":"5","pic":"/product_catalog/1449660885387.jpg"}],"hot_product_list":[{"brand_id":"72","brief":"","channel_id":"10","cover_price":"4.80","figure":"/1465268743242.jpg","name":"【艾漫】全职高手-蜜饯系列","p_catalog_id":"99","product_id":"6869","sell_time_end":"1465833600","sell_time_start":"1465228800","supplier_code":"300012","supplier_type":"1"},{"brand_id":"72","brief":"","channel_id":"3","cover_price":"25.00","figure":"/1464419271883.jpg","name":"【艾漫】全职高手 星座亚克力挂件","p_catalog_id":"26","product_id":"6698","sell_time_end":"1464969600","sell_time_start":"1464364800","supplier_code":"300011","supplier_type":"1"},{"brand_id":"72","brief":"预计2016年10月发货","channel_id":"3","cover_price":"12.00","figure":"/1470711927785.jpg","name":"【预售】【艾漫】全职高手-七夕心形徽章徽章","p_catalog_id":"90","product_id":"8629","sell_time_end":"1471276800","sell_time_start":"1470672000","supplier_code":"300011","supplier_type":"1"},{"brand_id":"5","brief":"3款可供选择，小仓送镊子、胶水等工具哦~","channel_id":"3","cover_price":"36.00","figure":"/1455775424896.jpg","name":"【智趣屋】DIY小屋盒子剧场 手工拼装房子 模型玩具创意礼品 送工具","p_catalog_id":"30","product_id":"4640","sell_time_end":"1456329600","sell_time_start":"1455724800","supplier_code":"0","supplier_type":"1"},{"brand_id":"5","brief":"此商品只作为补差价或邮费专用，小伙伴们不要乱拍呀~差几元拍几个~  o(*≧▽≦)ツ","channel_id":"3","cover_price":"1.00","figure":"/1452742997932.jpg","name":"尚硅谷邮费补拍专用万能拍【请不要乱拍哦~】","p_catalog_id":"100","product_id":"4063","sell_time_end":"1453305600","sell_time_start":"1452700800","supplier_code":"3300001","supplier_type":"2"},{"brand_id":"72","brief":"钥匙扣已开始陆续发货的说~ 不含底板~底板背板组合玩法可戳关联商品。","channel_id":"3","cover_price":"18.00","figure":"/1466157188535.jpg","name":"【官方正版】 全职高手 国家队系列挂件 Q版 亚克力 挂件--叶修","p_catalog_id":"26","product_id":"7238","sell_time_end":"1466697600","sell_time_start":"1466092800","supplier_code":"0","supplier_type":"1"}],"is_deleted":"0","name":"居家宅品","p_catalog_id":"5","parent_id":"0","pic":""}]
     */

    private int code;
    private String msg;
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
        /**
         * child : [{"is_deleted":"0","name":"优惠券","p_catalog_id":"115","parent_id":"5","pic":"/product_catalog/1469183837648.png"},{"is_deleted":"0","name":"家纺品","p_catalog_id":"81","parent_id":"5","pic":"/product_catalog/1446017029488.jpg"},{"is_deleted":"0","name":"眼镜布","p_catalog_id":"85","parent_id":"5","pic":"/product_catalog/1446017110058.jpg"},{"is_deleted":"0","name":"毛巾","p_catalog_id":"86","parent_id":"5","pic":"/product_catalog/1446017158779.jpg"},{"is_deleted":"0","name":"创意宅物","p_catalog_id":"90","parent_id":"5","pic":"/product_catalog/1446017175586.jpg"},{"is_deleted":"0","name":"零食","p_catalog_id":"99","parent_id":"5","pic":"/product_catalog/1449828955995.png"},{"is_deleted":"0","name":"节日特典","p_catalog_id":"100","parent_id":"5","pic":"/product_catalog/1450682473783.png"},{"is_deleted":"0","name":"桌游","p_catalog_id":"106","parent_id":"5","pic":"/product_catalog/1461814801527.jpg"},{"is_deleted":"0","name":"兵人","p_catalog_id":"107","parent_id":"5","pic":"/product_catalog/1465383093721.jpg"},{"is_deleted":"0","name":"BJD","p_catalog_id":"113","parent_id":"5","pic":"/product_catalog/1468306500168.jpg"},{"is_deleted":"0","name":"手办","p_catalog_id":"48","parent_id":"5","pic":"/product_catalog/1446017012089.jpg"},{"is_deleted":"0","name":"明信片","p_catalog_id":"23","parent_id":"5","pic":"/product_catalog/1446016836847.jpg"},{"is_deleted":"0","name":"书籍","p_catalog_id":"24","parent_id":"5","pic":"/product_catalog/1446016851701.jpg"},{"is_deleted":"0","name":"扭蛋/蛋盒","p_catalog_id":"25","parent_id":"5","pic":"/product_catalog/1446016874422.jpg"},{"is_deleted":"0","name":"挂件","p_catalog_id":"26","parent_id":"5","pic":"/product_catalog/1446016891091.jpg"},{"is_deleted":"0","name":"挂画海报","p_catalog_id":"27","parent_id":"5","pic":"/product_catalog/1446016906565.jpg"},{"is_deleted":"0","name":"餐具","p_catalog_id":"28","parent_id":"5","pic":"/product_catalog/1446016944877.jpg"},{"is_deleted":"0","name":"公仔","p_catalog_id":"29","parent_id":"5","pic":"/product_catalog/1446016961418.jpg"},{"is_deleted":"0","name":"雨伞","p_catalog_id":"17","parent_id":"5","pic":"/product_catalog/1446016762275.jpg"},{"is_deleted":"0","name":"DIY","p_catalog_id":"30","parent_id":"5","pic":"/product_catalog/1446016974517.jpg"},{"is_deleted":"0","name":"扇子","p_catalog_id":"31","parent_id":"5","pic":"/product_catalog/1446016989168.jpg"},{"is_deleted":"0","name":"抱枕","p_catalog_id":"22","parent_id":"5","pic":"/product_catalog/1446016784975.jpg"},{"is_deleted":"0","name":"等身抱枕","p_catalog_id":"83","parent_id":"5","pic":"/product_catalog/1446017074190.jpg"},{"is_deleted":"0","name":"护肤品","p_catalog_id":"98","parent_id":"5","pic":"/product_catalog/1449660885387.jpg"}]
         * hot_product_list : [{"brand_id":"72","brief":"","channel_id":"10","cover_price":"4.80","figure":"/1465268743242.jpg","name":"【艾漫】全职高手-蜜饯系列","p_catalog_id":"99","product_id":"6869","sell_time_end":"1465833600","sell_time_start":"1465228800","supplier_code":"300012","supplier_type":"1"},{"brand_id":"72","brief":"","channel_id":"3","cover_price":"25.00","figure":"/1464419271883.jpg","name":"【艾漫】全职高手 星座亚克力挂件","p_catalog_id":"26","product_id":"6698","sell_time_end":"1464969600","sell_time_start":"1464364800","supplier_code":"300011","supplier_type":"1"},{"brand_id":"72","brief":"预计2016年10月发货","channel_id":"3","cover_price":"12.00","figure":"/1470711927785.jpg","name":"【预售】【艾漫】全职高手-七夕心形徽章徽章","p_catalog_id":"90","product_id":"8629","sell_time_end":"1471276800","sell_time_start":"1470672000","supplier_code":"300011","supplier_type":"1"},{"brand_id":"5","brief":"3款可供选择，小仓送镊子、胶水等工具哦~","channel_id":"3","cover_price":"36.00","figure":"/1455775424896.jpg","name":"【智趣屋】DIY小屋盒子剧场 手工拼装房子 模型玩具创意礼品 送工具","p_catalog_id":"30","product_id":"4640","sell_time_end":"1456329600","sell_time_start":"1455724800","supplier_code":"0","supplier_type":"1"},{"brand_id":"5","brief":"此商品只作为补差价或邮费专用，小伙伴们不要乱拍呀~差几元拍几个~  o(*≧▽≦)ツ","channel_id":"3","cover_price":"1.00","figure":"/1452742997932.jpg","name":"尚硅谷邮费补拍专用万能拍【请不要乱拍哦~】","p_catalog_id":"100","product_id":"4063","sell_time_end":"1453305600","sell_time_start":"1452700800","supplier_code":"3300001","supplier_type":"2"},{"brand_id":"72","brief":"钥匙扣已开始陆续发货的说~ 不含底板~底板背板组合玩法可戳关联商品。","channel_id":"3","cover_price":"18.00","figure":"/1466157188535.jpg","name":"【官方正版】 全职高手 国家队系列挂件 Q版 亚克力 挂件--叶修","p_catalog_id":"26","product_id":"7238","sell_time_end":"1466697600","sell_time_start":"1466092800","supplier_code":"0","supplier_type":"1"}]
         * is_deleted : 0
         * name : 居家宅品
         * p_catalog_id : 5
         * parent_id : 0
         * pic :
         */

        private String is_deleted;
        private String name;
        private String p_catalog_id;
        private String parent_id;
        private String pic;
        private List<ChildBean> child;
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
            /**
             * is_deleted : 0
             * name : 优惠券
             * p_catalog_id : 115
             * parent_id : 5
             * pic : /product_catalog/1469183837648.png
             */

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
            /**
             * brand_id : 72
             * brief :
             * channel_id : 10
             * cover_price : 4.80
             * figure : /1465268743242.jpg
             * name : 【艾漫】全职高手-蜜饯系列
             * p_catalog_id : 99
             * product_id : 6869
             * sell_time_end : 1465833600
             * sell_time_start : 1465228800
             * supplier_code : 300012
             * supplier_type : 1
             */

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
