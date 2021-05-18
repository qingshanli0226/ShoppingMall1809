package com.example.net.bean;

import java.util.List;

public class RecommendBean {
    /**
     * code : 200
     * message : 请求成功
     * result : [{"name":"小裙子","url":"SKIRT_URL.json"},{"name":"夹克衫","url":"JACKET_URL.json"},{"name":"包包","url":"BAG_URL.json"}]
     */

    private String code;
    private String message;
    /**
     * name : 小裙子
     * url : SKIRT_URL.json
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
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
