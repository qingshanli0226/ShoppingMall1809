package com.fiannce.sql.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MessageBean {
    @Id(autoincrement = true)
    private Long id;
    private int type;
    private String message;
    private String messageTime;
    private boolean isRead;
    @Generated(hash = 1970959519)
    public MessageBean(Long id, int type, String message, String messageTime,
            boolean isRead) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.messageTime = messageTime;
        this.isRead = isRead;
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
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessageTime() {
        return this.messageTime;
    }
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
    public boolean getIsRead() {
        return this.isRead;
    }
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "id=" + id +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", messageTime='" + messageTime + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
