package com.example.framework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AddressTable {
    @Id(autoincrement = true)
    private Long addressId;
    //用户名
    private String name;
    //电话
    private String phone;
    //地址
    private String address;
    //默认是否选中
    private boolean isDefault;
    @Generated(hash = 710121467)
    public AddressTable(Long addressId, String name, String phone, String address,
            boolean isDefault) {
        this.addressId = addressId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isDefault = isDefault;
    }
    @Generated(hash = 180155216)
    public AddressTable() {
    }
    public Long getAddressId() {
        return this.addressId;
    }
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean getIsDefault() {
        return this.isDefault;
    }
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
