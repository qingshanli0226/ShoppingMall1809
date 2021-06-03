package com.example.common.bean;

public
class UserInfoBean {

    private String phone;
    private String address;
    private String name;

    public UserInfoBean(String phone, String address, String name) {
        this.phone = phone;
        this.address = address;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
