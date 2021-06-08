package com.example.manager;

import com.example.common.db.MessageTable;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private static MessageManager manager;

    public synchronized static MessageManager getInstance() {
        if (manager==null){
            manager=new MessageManager();
        }
        return manager;
    }
    //信息表集合
    private List<MessageTable> messageTableList = new ArrayList<>();
    //信息表接口
    private List<iMessageListener> messageListenerList = new ArrayList<>();

    public List<MessageTable> getMessageTableList() {
        return messageTableList;
    }

    public void setMessageTableList(List<MessageTable> messageTableList) {
        this.messageTableList = messageTableList;
        MessageNotify();
    }

    //添加信息
    public void addMessage(MessageTable messageTable){
        messageTableList.add(messageTable);
        MessageNotify();
    }
    //删除信息
    public void removeMessage(MessageTable messageTable){
        messageTableList.remove(messageTable);
        MessageNotify();
    }
    //修改信息
    public void UpdateMessage(MessageTable messageTable){
        for (MessageTable table : messageTableList) {
            if (table.getId()==messageTable.getId()){
                table.setIsShow(true);
            }
        }
        MessageNotify();
    }

    public interface iMessageListener{
        void OnMessage();
    }

    public void MessageNotify(){
        for (iMessageListener iMessageListener : messageListenerList) {
            iMessageListener.OnMessage();
        }
    }
}
