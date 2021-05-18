package com.example.net.bean;


import java.io.Serializable;

public class LoginBean implements Serializable {
    /**
     * code : 200
     * message : 登录成功
     * result : {"id":"111","name":"111","password":"111","email":null,"phone":null,"point":null,"address":null,"money":null,"avatar":"/img/1438946011155.jpg","token":"69a9f522-a13e-4bbf-87b5-4c828421b7f6AND1621384545009","gPassword":"0123456"}
     */

    private String code;
    private String message;
    private ResultBean result;

    @Override
    public String toString() {
        return "LoginBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", email=" + email +
                    ", phone=" + phone +
                    ", point=" + point +
                    ", address=" + address +
                    ", money=" + money +
                    ", avatar='" + avatar + '\'' +
                    ", token='" + token + '\'' +
                    ", gPassword='" + gPassword + '\'' +
                    '}';
        }

        /**
         * id : 111
         * name : 111
         * password : 111
         * email : null
         * phone : null
         * point : null
         * address : null
         * money : null
         * avatar : /img/1438946011155.jpg
         * token : 69a9f522-a13e-4bbf-87b5-4c828421b7f6AND1621384545009
         * gPassword : 0123456
         */

        private String id;
        private String name;
        private String password;
        private Object email;
        private Object phone;
        private Object point;
        private Object address;
        private Object money;
        private String avatar;
        private String token;
        private String gPassword;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getPoint() {
            return point;
        }

        public void setPoint(Object point) {
            this.point = point;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getGPassword() {
            return gPassword;
        }

        public void setGPassword(String gPassword) {
            this.gPassword = gPassword;
        }
    }

    //    /**
//     * code : 200
//     * message : 登录成功
//     * result : {"id":"123","name":"123","password":"123","email":null,"phone":"123","point":null,"address":"123","money":null,"avatar":"/img/1438946011155.jpg","token":"837856d3-e90b-4430-9232-889418b1e2deAND1619184342800","gPassword":null}
//     */
//
//    private String code;
//    private String message;
//    private ResultBean result;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public ResultBean getResult() {
//        return result;
//    }
//
//    public void setResult(ResultBean result) {
//        this.result = result;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    @Override
//    public String toString() {
//        return "LoginBean{" +
//                "code='" + code + '\'' +
//                ", message='" + message + '\'' +
//                ", result=" + result +
//                '}';
//    }
//
//    public static class ResultBean implements Serializable {
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public Object getEmail() {
//            return email;
//        }
//
//        public void setEmail(Object email) {
//            this.email = email;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//
//        public Object getPoint() {
//            return point;
//        }
//
//        public void setPoint(Object point) {
//            this.point = point;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public Object getMoney() {
//            return money;
//        }
//
//        public void setMoney(Object money) {
//            this.money = money;
//        }
//
//        public String getAvatar() {
//            return avatar;
//        }
//
//        public void setAvatar(String avatar) {
//            this.avatar = avatar;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//
//        @Override
//        public String toString() {
//            return "ResultBean{" +
//                    "id='" + id + '\'' +
//                    ", name='" + name + '\'' +
//                    ", password='" + password + '\'' +
//                    ", email=" + email +
//                    ", phone='" + phone + '\'' +
//                    ", point=" + point +
//                    ", address='" + address + '\'' +
//                    ", money=" + money +
//                    ", avatar='" + avatar + '\'' +
//                    ", token='" + token + '\'' +
//                    ", gPassword=" + gPassword +
//                    '}';
//        }
//
//        public Object getgPassword() {
//            return gPassword;
//        }
//
//        public void setgPassword(Object gPassword) {
//            this.gPassword = gPassword;
//        }
//
//        /**
//         * id : 123
//         * name : 123
//         * password : 123
//         * email : null
//         * phone : 123
//         * point : null
//         * address : 123
//         * money : null
//         * avatar : /img/1438946011155.jpg
//         * token : 837856d3-e90b-4430-9232-889418b1e2deAND1619184342800
//         * gPassword : null
//         */
//
//        private String id;
//        private String name;
//        private String password;
//        private Object email;
//        private String phone;
//        private Object point;
//        private String address;
//        private Object money;
//        private String avatar;
//        private String token;
//        private Object gPassword;
//    }
}
