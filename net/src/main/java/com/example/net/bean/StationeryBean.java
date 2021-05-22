package com.example.net.bean;

import java.util.List;

public class StationeryBean {
    /**
     * code : 200
     * msg : 请求成功
     * result : [{"p_catalog_id":"6","parent_id":"0","name":"办公文具","pic":"","is_deleted":"0","child":[{"p_catalog_id":"32","parent_id":"6","name":"笔记本","pic":"/product_catalog/1446017226590.jpg","is_deleted":"0"},{"p_catalog_id":"33","parent_id":"6","name":"文件夹","pic":"/product_catalog/1446017259628.jpg","is_deleted":"0"},{"p_catalog_id":"34","parent_id":"6","name":"便签本","pic":"/product_catalog/1446017278468.jpg","is_deleted":"0"},{"p_catalog_id":"35","parent_id":"6","name":"笔袋笔筒","pic":"/product_catalog/1446017289189.jpg","is_deleted":"0"},{"p_catalog_id":"37","parent_id":"6","name":"卡套/卡贴","pic":"/product_catalog/1446017305527.jpg","is_deleted":"0"},{"p_catalog_id":"38","parent_id":"6","name":"和风纸胶带","pic":"/product_catalog/1446017320396.jpg","is_deleted":"0"},{"p_catalog_id":"87","parent_id":"6","name":"笔墨","pic":"/product_catalog/1446017336258.jpg","is_deleted":"0"}],"hot_product_list":[{"product_id":"14","channel_id":"11","brand_id":"3","p_catalog_id":"32","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔的周边】剑网3 剑侠情缘叁门派压皮活页笔记本","cover_price":"29.00","brief":"<p> 剑网三 剑3 压皮活页笔记本<br /><\/p><p>出品：小迷兔的周边<\/p>","figure":"/1471501055587.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"6298","channel_id":"11","brand_id":"5","p_catalog_id":"38","supplier_type":"1","supplier_code":"2101002","name":"【歪瓜出品】 符文纸胶带 道士符纸 封印中二 手账装饰胶带","cover_price":"15.00","brief":"","figure":"/1462531089095.jpg","sell_time_start":"1462464000","sell_time_end":"1463068800"},{"product_id":"6293","channel_id":"11","brand_id":"5","p_catalog_id":"32","supplier_type":"1","supplier_code":"2101002","name":"预售【歪瓜出品】 五年高考三年模拟笔记 创意文具日记本记事本 流行梗","cover_price":"15.00","brief":"6月中旬发货~","figure":"/1462530064674.jpg","sell_time_start":"1462464000","sell_time_end":"1463068800"},{"product_id":"10","channel_id":"4","brand_id":"3","p_catalog_id":"37","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔】剑侠情缘叁 剑网3 剑三门派动物硅胶卡套","cover_price":"15.00","brief":"出品：小迷兔的周边 ~卡套只带胶带，无挂绳噢♪(^∇^*)","figure":"/1471501518349.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"235","channel_id":"11","brand_id":"33","p_catalog_id":"32","supplier_type":"1","supplier_code":"0","name":"【orz漫工厂】COSPLAY道具 夏目友人帐本 记事薄记事笔记本子","cover_price":"12.00","brief":"","figure":"/1435116836598.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"614","channel_id":"6","brand_id":"5","p_catalog_id":"32","supplier_type":"1","supplier_code":"0","name":"【岁月坊】手工本水墨风之墨荷","cover_price":"9.90","brief":"","figure":"/1438946011155.jpg","sell_time_start":"1438876800","sell_time_end":"1439481600"}]}]
     */

    private int code;
    private String msg;
    /**
     * p_catalog_id : 6
     * parent_id : 0
     * name : 办公文具
     * pic :
     * is_deleted : 0
     * child : [{"p_catalog_id":"32","parent_id":"6","name":"笔记本","pic":"/product_catalog/1446017226590.jpg","is_deleted":"0"},{"p_catalog_id":"33","parent_id":"6","name":"文件夹","pic":"/product_catalog/1446017259628.jpg","is_deleted":"0"},{"p_catalog_id":"34","parent_id":"6","name":"便签本","pic":"/product_catalog/1446017278468.jpg","is_deleted":"0"},{"p_catalog_id":"35","parent_id":"6","name":"笔袋笔筒","pic":"/product_catalog/1446017289189.jpg","is_deleted":"0"},{"p_catalog_id":"37","parent_id":"6","name":"卡套/卡贴","pic":"/product_catalog/1446017305527.jpg","is_deleted":"0"},{"p_catalog_id":"38","parent_id":"6","name":"和风纸胶带","pic":"/product_catalog/1446017320396.jpg","is_deleted":"0"},{"p_catalog_id":"87","parent_id":"6","name":"笔墨","pic":"/product_catalog/1446017336258.jpg","is_deleted":"0"}]
     * hot_product_list : [{"product_id":"14","channel_id":"11","brand_id":"3","p_catalog_id":"32","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔的周边】剑网3 剑侠情缘叁门派压皮活页笔记本","cover_price":"29.00","brief":"<p> 剑网三 剑3 压皮活页笔记本<br /><\/p><p>出品：小迷兔的周边<\/p>","figure":"/1471501055587.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"6298","channel_id":"11","brand_id":"5","p_catalog_id":"38","supplier_type":"1","supplier_code":"2101002","name":"【歪瓜出品】 符文纸胶带 道士符纸 封印中二 手账装饰胶带","cover_price":"15.00","brief":"","figure":"/1462531089095.jpg","sell_time_start":"1462464000","sell_time_end":"1463068800"},{"product_id":"6293","channel_id":"11","brand_id":"5","p_catalog_id":"32","supplier_type":"1","supplier_code":"2101002","name":"预售【歪瓜出品】 五年高考三年模拟笔记 创意文具日记本记事本 流行梗","cover_price":"15.00","brief":"6月中旬发货~","figure":"/1462530064674.jpg","sell_time_start":"1462464000","sell_time_end":"1463068800"},{"product_id":"10","channel_id":"4","brand_id":"3","p_catalog_id":"37","supplier_type":"1","supplier_code":"1101036","name":"【小迷兔】剑侠情缘叁 剑网3 剑三门派动物硅胶卡套","cover_price":"15.00","brief":"出品：小迷兔的周边 ~卡套只带胶带，无挂绳噢♪(^∇^*)","figure":"/1471501518349.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"235","channel_id":"11","brand_id":"33","p_catalog_id":"32","supplier_type":"1","supplier_code":"0","name":"【orz漫工厂】COSPLAY道具 夏目友人帐本 记事薄记事笔记本子","cover_price":"12.00","brief":"","figure":"/1435116836598.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"614","channel_id":"6","brand_id":"5","p_catalog_id":"32","supplier_type":"1","supplier_code":"0","name":"【岁月坊】手工本水墨风之墨荷","cover_price":"9.90","brief":"","figure":"/1438946011155.jpg","sell_time_start":"1438876800","sell_time_end":"1439481600"}]
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
         * p_catalog_id : 32
         * parent_id : 6
         * name : 笔记本
         * pic : /product_catalog/1446017226590.jpg
         * is_deleted : 0
         */

        private List<ChildBean> child;
        /**
         * product_id : 14
         * channel_id : 11
         * brand_id : 3
         * p_catalog_id : 32
         * supplier_type : 1
         * supplier_code : 1101036
         * name : 【小迷兔的周边】剑网3 剑侠情缘叁门派压皮活页笔记本
         * cover_price : 29.00
         * brief : <p> 剑网三 剑3 压皮活页笔记本<br /></p><p>出品：小迷兔的周边</p>
         * figure : /1471501055587.jpg
         * sell_time_start : 0
         * sell_time_end : 0
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
