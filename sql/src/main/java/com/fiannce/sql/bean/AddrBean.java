package com.fiannce.sql.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class AddrBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String addr;
    private String phone;
    private boolean isDefault;
    @Generated(hash = 2036087478)
    public AddrBean(Long id, String name, String addr, String phone,
            boolean isDefault) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.isDefault = isDefault;
    }
    @Generated(hash = 306476614)
    public AddrBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddr() {
        return this.addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean getIsDefault() {
        return this.isDefault;
    }
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }


}
