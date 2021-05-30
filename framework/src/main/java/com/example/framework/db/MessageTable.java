package com.example.framework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class MessageTable {

    @Id(autoincrement = true)
    private Long messageId;

    private String message;
    private String time;
    private boolean isRead;
    @Generated(hash = 1519840136)
    public MessageTable(Long messageId, String message, String time,
            boolean isRead) {
        this.messageId = messageId;
        this.message = message;
        this.time = time;
        this.isRead = isRead;
    }
    @Generated(hash = 1805713138)
    public MessageTable() {
    }
    public Long getMessageId() {
        return this.messageId;
    }
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public boolean getIsRead() {
        return this.isRead;
    }
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
 

}
