package com.example.common.db;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class MessageTable {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String isSucceed;
    @NotNull
    private Long messageTime;
    @NotNull
    private boolean isShow;
    @Generated(hash = 702435893)
    public MessageTable(Long id, @NotNull String isSucceed,
            @NotNull Long messageTime, boolean isShow) {
        this.id = id;
        this.isSucceed = isSucceed;
        this.messageTime = messageTime;
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
                ", isSucceed='" + isSucceed + '\'' +
                ", messageTime=" + messageTime +
                ", isShow=" + isShow +
                '}';
    }
}
