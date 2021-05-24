package com.example.net.bean.classify;

import java.util.List;

public class PantsBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"p_catalog_id":"57","parent_id":"0","name":"下装","pic":"","is_deleted":"0","child":[{"p_catalog_id":"110","parent_id":"57","name":"汉风","pic":"/product_catalog/1465383117300.jpg","is_deleted":"0"},{"p_catalog_id":"59","parent_id":"57","name":"古风","pic":"/product_catalog/1446017608030.jpg","is_deleted":"0"},{"p_catalog_id":"61","parent_id":"57","name":"lolita","pic":"/product_catalog/1446017628543.jpg","is_deleted":"0"},{"p_catalog_id":"62","parent_id":"57","name":"胖次","pic":"/product_catalog/1446017713072.jpg","is_deleted":"0"},{"p_catalog_id":"63","parent_id":"57","name":"南瓜裤","pic":"/product_catalog/1446017725424.jpg","is_deleted":"0"},{"p_catalog_id":"88","parent_id":"57","name":"日常","pic":"/product_catalog/1446017744373.jpg","is_deleted":"0"}],"hot_product_list":[{"product_id":"6634","channel_id":"8","brand_id":"5","p_catalog_id":"88","supplier_type":"2","supplier_code":"1101037","name":"【画影】小狐狸短裙","cover_price":"98.00","brief":"红黑短裙是现货哦~","figure":"/1466759853976.jpg","sell_time_start":"1464019200","sell_time_end":"1464624000"},{"product_id":"6897","channel_id":"15","brand_id":"402","p_catalog_id":"88","supplier_type":"2","supplier_code":"802004","name":"【流烟昔泠】汉元素 半臂 短宋裤 吊带 刺绣 豆蔻年华少女系列-清秋兔 短宋裤","cover_price":"99.00","brief":"7月15日起进入第四批预定，第四批约7月31日左右发货","figure":"/1465296453463.jpg","sell_time_start":"1465228800","sell_time_end":"1465833600"},{"product_id":"2215","channel_id":"6","brand_id":"215","p_catalog_id":"59","supplier_type":"1","supplier_code":"1101015","name":"【游鹤工作室】闲梦沄沄下裙刺绣白鹿（短款）- 藏青","cover_price":"110.60","brief":"现货 ","figure":"/1444883979497.jpg","sell_time_start":"1444838400","sell_time_end":"1445443200"},{"product_id":"5914","channel_id":"8","brand_id":"234","p_catalog_id":"88","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】原创设计 百鬼夜行-冥府魔道和风短裤 日式棉麻阔腿裤A70","cover_price":"89.00","brief":"","figure":"/1462352934101.jpg","sell_time_start":"1460476800","sell_time_end":"1461081600"},{"product_id":"5614","channel_id":"8","brand_id":"234","p_catalog_id":"88","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】原创 日本和风服饰-和。扇 棉麻印花短裤 女装阔腿裤 A11","cover_price":"78.00","brief":"预售截止到2号发货哦~ 预售截止到2号发货哦~ 预售截止到2号发货哦~","figure":"/1459135580746.jpg","sell_time_start":"1459094400","sell_time_end":"1459699200"},{"product_id":"4011","channel_id":"8","brand_id":"234","p_catalog_id":"88","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】定制款 欧式复古歌特风系带高腰短裙 半身裙子 伞裙 A52","cover_price":"79.00","brief":"","figure":"/1452653909042.jpg","sell_time_start":"1452614400","sell_time_end":"1453219200"}]}]
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
         * p_catalog_id : 57
         * parent_id : 0
         * name : 下装
         * pic :
         * is_deleted : 0
         * child : [{"p_catalog_id":"110","parent_id":"57","name":"汉风","pic":"/product_catalog/1465383117300.jpg","is_deleted":"0"},{"p_catalog_id":"59","parent_id":"57","name":"古风","pic":"/product_catalog/1446017608030.jpg","is_deleted":"0"},{"p_catalog_id":"61","parent_id":"57","name":"lolita","pic":"/product_catalog/1446017628543.jpg","is_deleted":"0"},{"p_catalog_id":"62","parent_id":"57","name":"胖次","pic":"/product_catalog/1446017713072.jpg","is_deleted":"0"},{"p_catalog_id":"63","parent_id":"57","name":"南瓜裤","pic":"/product_catalog/1446017725424.jpg","is_deleted":"0"},{"p_catalog_id":"88","parent_id":"57","name":"日常","pic":"/product_catalog/1446017744373.jpg","is_deleted":"0"}]
         * hot_product_list : [{"product_id":"6634","channel_id":"8","brand_id":"5","p_catalog_id":"88","supplier_type":"2","supplier_code":"1101037","name":"【画影】小狐狸短裙","cover_price":"98.00","brief":"红黑短裙是现货哦~","figure":"/1466759853976.jpg","sell_time_start":"1464019200","sell_time_end":"1464624000"},{"product_id":"6897","channel_id":"15","brand_id":"402","p_catalog_id":"88","supplier_type":"2","supplier_code":"802004","name":"【流烟昔泠】汉元素 半臂 短宋裤 吊带 刺绣 豆蔻年华少女系列-清秋兔 短宋裤","cover_price":"99.00","brief":"7月15日起进入第四批预定，第四批约7月31日左右发货","figure":"/1465296453463.jpg","sell_time_start":"1465228800","sell_time_end":"1465833600"},{"product_id":"2215","channel_id":"6","brand_id":"215","p_catalog_id":"59","supplier_type":"1","supplier_code":"1101015","name":"【游鹤工作室】闲梦沄沄下裙刺绣白鹿（短款）- 藏青","cover_price":"110.60","brief":"现货 ","figure":"/1444883979497.jpg","sell_time_start":"1444838400","sell_time_end":"1445443200"},{"product_id":"5914","channel_id":"8","brand_id":"234","p_catalog_id":"88","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】原创设计 百鬼夜行-冥府魔道和风短裤 日式棉麻阔腿裤A70","cover_price":"89.00","brief":"","figure":"/1462352934101.jpg","sell_time_start":"1460476800","sell_time_end":"1461081600"},{"product_id":"5614","channel_id":"8","brand_id":"234","p_catalog_id":"88","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】原创 日本和风服饰-和。扇 棉麻印花短裤 女装阔腿裤 A11","cover_price":"78.00","brief":"预售截止到2号发货哦~ 预售截止到2号发货哦~ 预售截止到2号发货哦~","figure":"/1459135580746.jpg","sell_time_start":"1459094400","sell_time_end":"1459699200"},{"product_id":"4011","channel_id":"8","brand_id":"234","p_catalog_id":"88","supplier_type":"2","supplier_code":"2101001","name":"【古怪舍】定制款 欧式复古歌特风系带高腰短裙 半身裙子 伞裙 A52","cover_price":"79.00","brief":"","figure":"/1452653909042.jpg","sell_time_start":"1452614400","sell_time_end":"1453219200"}]
         */

        private String p_catalog_id;
        private String parent_id;
        private String name;
        private String pic;
        private String is_deleted;
        private List<ChildBean> child;
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
            /**
             * p_catalog_id : 110
             * parent_id : 57
             * name : 汉风
             * pic : /product_catalog/1465383117300.jpg
             * is_deleted : 0
             */

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
            /**
             * product_id : 6634
             * channel_id : 8
             * brand_id : 5
             * p_catalog_id : 88
             * supplier_type : 2
             * supplier_code : 1101037
             * name : 【画影】小狐狸短裙
             * cover_price : 98.00
             * brief : 红黑短裙是现货哦~
             * figure : /1466759853976.jpg
             * sell_time_start : 1464019200
             * sell_time_end : 1464624000
             */

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
