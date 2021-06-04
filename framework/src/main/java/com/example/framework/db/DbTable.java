package com.example.framework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DbTable {
    @Id(autoincrement = true)
    private Long id;

    private String msgType;
    private String title;
    private String msg;
    private String time;
    private boolean isNew;
    @Generated(hash = 705028539)
    public DbTable(Long id, String msgType, String title, String msg, String time,
            boolean isNew) {
        this.id = id;
        this.msgType = msgType;
        this.title = title;
        this.msg = msg;
        this.time = time;
        this.isNew = isNew;
    }
    @Generated(hash = 1366068159)
    public DbTable() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMsgType() {
        return this.msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
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
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public boolean getIsNew() {
        return this.isNew;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "DbTable{" +
                "id=" + id +
                ", msgType='" + msgType + '\'' +
                ", title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", isNew=" + isNew +
                '}';
    }
}
