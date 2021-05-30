package com.example.electricityproject.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MessageTable {


    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String payMessage;
    private long time;
    private boolean isShow;
    @Generated(hash = 44579754)
    public MessageTable(Long id, @NotNull String payMessage, long time,
            boolean isShow) {
        this.id = id;
        this.payMessage = payMessage;
        this.time = time;
        this.isShow = isShow;
    }
    @Generated(hash = 1805713138)
    public MessageTable() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPayMessage() {
        return this.payMessage;
    }
    public void setPayMessage(String payMessage) {
        this.payMessage = payMessage;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public boolean getIsShow() {
        return this.isShow;
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "MessageTable{" +
                "id=" + id +
                ", payMessage='" + payMessage + '\'' +
                ", time=" + time +
                ", isShow=" + isShow +
                '}';
    }
}
