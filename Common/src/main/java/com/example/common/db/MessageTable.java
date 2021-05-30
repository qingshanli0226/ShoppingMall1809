package com.example.common.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MessageTable {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String isSucceed;
    @NotNull
    private Long messageTime;
    @Generated(hash = 275714443)
    public MessageTable(Long id, @NotNull String isSucceed,
            @NotNull Long messageTime) {
        this.id = id;
        this.isSucceed = isSucceed;
        this.messageTime = messageTime;
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
    public String getIsSucceed() {
        return this.isSucceed;
    }
    public void setIsSucceed(String isSucceed) {
        this.isSucceed = isSucceed;
    }
    public Long getMessageTime() {
        return this.messageTime;
    }
    public void setMessageTime(Long messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "MessageTable{" +
                "id=" + id +
                ", isSucceed='" + isSucceed + '\'' +
                ", messageTime=" + messageTime +
                '}';
    }
}
