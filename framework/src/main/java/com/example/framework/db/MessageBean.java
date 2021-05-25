package com.example.framework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MessageBean {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String msg;
    private boolean isnew;
    private long time;
    @Generated(hash = 132519769)
    public MessageBean(Long id, String title, String msg, boolean isnew,
            long time) {
        this.id = id;
        this.title = title;
        this.msg = msg;
        this.isnew = isnew;
        this.time = time;
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
    public boolean getIsnew() {
        return this.isnew;
    }
    public void setIsnew(boolean isnew) {
        this.isnew = isnew;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
  
}
