package com.example.electricityproject.shopp.userinfo.infodb;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserInfoTable {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String name;
    private String address;
    private String phone;
    private boolean isShow;
    @Generated(hash = 322132368)
    public UserInfoTable(Long id, @NotNull String name, String address,
            String phone, boolean isShow) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isShow = isShow;
    }
    @Generated(hash = 1354492153)
    public UserInfoTable() {
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
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean getIsShow() {
        return this.isShow;
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    

}
