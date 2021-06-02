package com.example.framework.manager;

import com.example.framework.db.MessageBean;

import java.util.ArrayList;
import java.util.List;

public class MsgManager {
    private static MsgManager msgManager;
    private List<MessageBean> messageBean=new ArrayList<>();
    public MsgManager() {
    }

    public static MsgManager getInstance() {
        if (msgManager==null){
            msgManager=new MsgManager();
        }
        return msgManager;
    }

    public List<MessageBean> getMessageBean() {
        return messageBean;
    }

    public interface IMessageListenner{
        void onResult(boolean isSuccess,List<MessageBean>messageBeanList);
    }


}
