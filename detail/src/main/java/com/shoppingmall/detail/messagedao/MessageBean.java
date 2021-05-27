package com.shoppingmall.detail.messagedao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MessageBean {
    @Id(autoincrement = true)
    private Long id;

    private String title;
    private String msg;
    private boolean isNew;
    private long time;
    private String reserveOne;
    private String reserveTwo;
    @Generated(hash = 351370712)
    public MessageBean(Long id, String title, String msg, boolean isNew, long time,
            String reserveOne, String reserveTwo) {
        this.id = id;
        this.title = title;
        this.msg = msg;
        this.isNew = isNew;
        this.time = time;
        this.reserveOne = reserveOne;
        this.reserveTwo = reserveTwo;
    }
    @Generated(hash = 1588632019)
    public MessageBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public boolean getIsNew() {
        return this.isNew;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getReserveOne() {
        return this.reserveOne;
    }
    public void setReserveOne(String reserveOne) {
        this.reserveOne = reserveOne;
    }
    public String getReserveTwo() {
        return this.reserveTwo;
    }
    public void setReserveTwo(String reserveTwo) {
        this.reserveTwo = reserveTwo;
    }
    
}
