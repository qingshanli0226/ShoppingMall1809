package com.example.framework.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CacheMessage {
    @Id(autoincrement = true)
    private Long messageId;

    private String message;
    private String time;
    private boolean isRead;
    @Generated(hash = 172547459)
    public CacheMessage(Long messageId, String message, String time,
            boolean isRead) {
        this.messageId = messageId;
        this.message = message;
        this.time = time;
        this.isRead = isRead;
    }
    @Generated(hash = 1110904812)
    public CacheMessage() {
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
