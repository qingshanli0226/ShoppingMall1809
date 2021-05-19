package com.example.net.bean.store;

import java.util.List;

public class WenjuStoreBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : {"page_data":[{"product_id":"10557","origin_price":"0.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】【帝都】IDO 20 动漫游戏嘉年华","cover_price":"50.00","brief":"","figure":"/1478596679261.jpg","sell_time_start":"1478596281","sell_time_end":"1479139200"},{"product_id":"7583","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-10--13:30","cover_price":"280.00","brief":"","figure":"/1466996276064.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7584","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-10--19:30","cover_price":"280.00","brief":"","figure":"/1466996492134.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7585","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-11--13:30","cover_price":"280.00","brief":"","figure":"/1466996935443.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"8888","origin_price":"50.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】I DO 18中国（北京）动漫游戏嘉年华漫展门票","cover_price":"50.00","brief":"","figure":"/1472437083583.jpg","sell_time_start":"1471881600","sell_time_end":"1472486400"},{"product_id":"7586","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-11--19:30","cover_price":"280.00","brief":"","figure":"/1466997050407.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7582","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-09--19:30","cover_price":"280.00","brief":"","figure":"/1466995768005.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7503","origin_price":"35.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】第二届天津大能猫动漫节","cover_price":"35.00","brief":"","figure":"/1466664921153.jpg","sell_time_start":"1466611200","sell_time_end":"1467216000"},{"product_id":"10023","origin_price":"50.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】北京·囧神19圣诞趴！12/24~25和你一齐嗨皮！","cover_price":"50.00","brief":"","figure":"/1476943798642.jpg","sell_time_start":"1476892800","sell_time_end":"1477497600"},{"product_id":"10059","origin_price":"35.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】【妖都】青宫动漫嘉年华Comic member04","cover_price":"35.00","brief":"","figure":"/1478760430805.jpg","sell_time_start":"1476979200","sell_time_end":"1477584000"},{"product_id":"9845","origin_price":"45.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【CA14】武汉ComiAi动漫游戏同人博览会14 电子票","cover_price":"45.00","brief":"","figure":"/1476414516806.jpg","sell_time_start":"1476374400","sell_time_end":"1476979200"}],"catalog_data":false,"brand_data":false,"is_recommended":"0"}
     */

    private int code;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * page_data : [{"product_id":"10557","origin_price":"0.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】【帝都】IDO 20 动漫游戏嘉年华","cover_price":"50.00","brief":"","figure":"/1478596679261.jpg","sell_time_start":"1478596281","sell_time_end":"1479139200"},{"product_id":"7583","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-10--13:30","cover_price":"280.00","brief":"","figure":"/1466996276064.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7584","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-10--19:30","cover_price":"280.00","brief":"","figure":"/1466996492134.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7585","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-11--13:30","cover_price":"280.00","brief":"","figure":"/1466996935443.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"8888","origin_price":"50.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】I DO 18中国（北京）动漫游戏嘉年华漫展门票","cover_price":"50.00","brief":"","figure":"/1472437083583.jpg","sell_time_start":"1471881600","sell_time_end":"1472486400"},{"product_id":"7586","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-11--19:30","cover_price":"280.00","brief":"","figure":"/1466997050407.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7582","origin_price":"280.00","channel_id":"9","brand_id":"51","p_catalog_id":"93","supplier_type":"1","supplier_code":"0","name":"Live Spectacle《NARUTO-火影忍者-》World Tour中国巡演\u2014广州站-16-12-09--19:30","cover_price":"280.00","brief":"","figure":"/1466995768005.jpg","sell_time_start":"1466956800","sell_time_end":"1467561600"},{"product_id":"7503","origin_price":"35.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】第二届天津大能猫动漫节","cover_price":"35.00","brief":"","figure":"/1466664921153.jpg","sell_time_start":"1466611200","sell_time_end":"1467216000"},{"product_id":"10023","origin_price":"50.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】北京·囧神19圣诞趴！12/24~25和你一齐嗨皮！","cover_price":"50.00","brief":"","figure":"/1476943798642.jpg","sell_time_start":"1476892800","sell_time_end":"1477497600"},{"product_id":"10059","origin_price":"35.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【电子票】【妖都】青宫动漫嘉年华Comic member04","cover_price":"35.00","brief":"","figure":"/1478760430805.jpg","sell_time_start":"1476979200","sell_time_end":"1477584000"},{"product_id":"9845","origin_price":"45.00","channel_id":"9","brand_id":"5","p_catalog_id":"93","supplier_type":"2","supplier_code":"3300001","name":"【CA14】武汉ComiAi动漫游戏同人博览会14 电子票","cover_price":"45.00","brief":"","figure":"/1476414516806.jpg","sell_time_start":"1476374400","sell_time_end":"1476979200"}]
         * catalog_data : false
         * brand_data : false
         * is_recommended : 0
         */

        private boolean catalog_data;
        private boolean brand_data;
        private String is_recommended;
        private List<PageDataBean> page_data;

        public boolean isCatalog_data() {
            return catalog_data;
        }

        public void setCatalog_data(boolean catalog_data) {
            this.catalog_data = catalog_data;
        }

        public boolean isBrand_data() {
            return brand_data;
        }

        public void setBrand_data(boolean brand_data) {
            this.brand_data = brand_data;
        }

        public String getIs_recommended() {
            return is_recommended;
        }

        public void setIs_recommended(String is_recommended) {
            this.is_recommended = is_recommended;
        }

        public List<PageDataBean> getPage_data() {
            return page_data;
        }

        public void setPage_data(List<PageDataBean> page_data) {
            this.page_data = page_data;
        }

        public static class PageDataBean {
            /**
             * product_id : 10557
             * origin_price : 0.00
             * channel_id : 9
             * brand_id : 5
             * p_catalog_id : 93
             * supplier_type : 2
             * supplier_code : 3300001
             * name : 【电子票】【帝都】IDO 20 动漫游戏嘉年华
             * cover_price : 50.00
             * brief :
             * figure : /1478596679261.jpg
             * sell_time_start : 1478596281
             * sell_time_end : 1479139200
             */

            private String product_id;
            private String origin_price;
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

            public String getOrigin_price() {
                return origin_price;
            }

            public void setOrigin_price(String origin_price) {
                this.origin_price = origin_price;
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
