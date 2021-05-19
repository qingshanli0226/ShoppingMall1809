package com.fiannce.bawei.net.mode;

public class VersionBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : {"version":"1.2","apkUrl":"http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk","desc":"解决一些bug, 优化网络请求!","force":true,"versionCode":2,"apkHash":"59b2f08ca1598978c4f779e3e60ee90d"}
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
         * version : 1.2
         * apkUrl : http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk
         * desc : 解决一些bug, 优化网络请求!
         * force : true
         * versionCode : 2
         * apkHash : 59b2f08ca1598978c4f779e3e60ee90d
         */

        private String version;
        private String apkUrl;
        private String desc;
        private boolean force;
        private int versionCode;
        private String apkHash;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public boolean isForce() {
            return force;
        }

        public void setForce(boolean force) {
            this.force = force;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getApkHash() {
            return apkHash;
        }

        public void setApkHash(String apkHash) {
            this.apkHash = apkHash;
        }
    }
}
