package com.example.common.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MessageDataBase {

    private static MessageDataBase messageManger;
    private List<iMessageListener> messageListeners =new ArrayList<>();

    public static synchronized MessageDataBase getInstance() {
        if(messageManger==null){
            messageManger = new MessageDataBase();
        }
        return messageManger;
    }

    private DaoMaster daoMaster;
    private MessageTableDao messageTableDao;
    private Context mContext;

    public void init(Context context){
        this.mContext=context;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, "commodity");
            daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());
        }
        return daoMaster;
    }
    //支付信息的数据库
    public synchronized MessageTableDao getMessageTableDao() {
            if (messageTableDao == null) {
                messageTableDao = daoMaster.newSession().getMessageTableDao();
            }
        return messageTableDao;
    }
    //添加支付信息后遍历注册接口发送通知
    public void payInsert(MessageTable messageTable){
        getMessageTableDao().insert(messageTable);
        MessageNotify();
    }
    //删除支付信息后遍历注册接口发送通知
    public void payRemove(MessageTable messageTable){
        getMessageTableDao().delete(messageTable);
        MessageNotify();
    }
    //修改支付信息后遍历注册接口发送通知
    public void payUpdate(MessageTable messageTable){
        getMessageTableDao().update(new MessageTable(Long.decode(messageTable.getId()+""),messageTable.getPayMessage(),messageTable.getIsSucceed(),messageTable.getMessageTime(),true));
        MessageNotify();
    }
    //查询所有支付的信息
    public List<MessageTable> payLoadAll(){
        List<MessageTable> messageTables = getMessageTableDao().loadAll();
        return messageTables;
    }
    //注册
    public synchronized void register(MessageDataBase.iMessageListener iMessageListener){
        synchronized (MessageDataBase.class) {
            messageListeners.add(iMessageListener);
        }
    }
    //取消注册
    public void unregister(MessageDataBase.iMessageListener iMessageListener){
        synchronized (MessageDataBase.class) {
            messageListeners.remove(iMessageListener);
        }
    }
    //支付信息接口刷新
    public void MessageNotify(){
        for (MessageDataBase.iMessageListener messageListener : messageListeners) {
            messageListener.onMessageNumListener();
        }
    }

    public interface iMessageListener {
        void onMessageNumListener();
    }

}
