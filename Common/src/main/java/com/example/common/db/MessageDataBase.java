package com.example.common.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MessageDataBase {

    private static MessageDataBase messageManger;
    private IMessageListener iMessageListener;
    private List<IMessageListener> list=new ArrayList<>();

    public static synchronized MessageDataBase getInstance() {
        if(messageManger==null){
            messageManger = new MessageDataBase();
        }
        return messageManger;
    }

    private DaoMaster daoMaster;
    private DaoSession daoSession;
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

    public DaoSession getDaoSession() {
        synchronized (MessageDataBase.class) {
            if (daoSession == null) {
                daoSession = daoMaster.newSession();
            }

            for (IMessageListener messageListener : list) {
                messageListener.onMessageNumListener();
            }
        }

        return daoSession;

    }

    //注册
    public synchronized void register(IMessageListener iMessageListener){
        synchronized (MessageDataBase.class) {
            list.add(iMessageListener);
        }
    }
    //取消注册
    public  void unregister(IMessageListener iMessageListener){
        synchronized (MessageDataBase.class) {
            list.remove(iMessageListener);
        }
    }


    public interface IMessageListener{
        void onMessageNumListener();
    }

}
