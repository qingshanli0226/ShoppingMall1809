package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.commom.ShopConstants;
import com.example.framework.db.DaoMaster;
import com.example.framework.db.DaoSession;
import com.example.framework.db.MessageTable;

import java.util.List;


public class MessageManager {

    private Context context;
    private DaoSession daoSession;

    public void init(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, ShopConstants.SQ_MANAGE);
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();

        //初始化SP
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        if (count==-1){
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ShopConstants.SP_MANAGE_NAME,0);
            edit.commit();
        }
    }

    //获取所有
    public synchronized List<MessageTable>  getMessage() {
        return  daoSession.loadAll(MessageTable.class);
    }
    //添加一个
    public synchronized void setMessage(MessageTable message) {
        daoSession.insert(message);
    }

    //添加个数
    public synchronized boolean addCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        if (count!=-1){
            ++count;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ShopConstants.SP_MANAGE_NAME,count);
            edit.commit();
            if (message != null) {
                message.onMessage(count);
            }
            return true;
        }
        return false;
    }

    public synchronized boolean subCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        if (count!=-1){
            --count;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ShopConstants.SP_MANAGE_NAME,count);
            edit.commit();
            if (message != null) {
                message.onMessage(count);
            }
            return true;
        }
        return false;
    }
    //数量
    public synchronized int getCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        return count;
    }
    //修改
    public synchronized void upDataMessage(MessageTable messageTable){
        daoSession.update(messageTable);
    }

    public interface IMessage{
        void onMessage(int count);
    }
    private IMessage message;

    public void register(IMessage message){
        this.message = message;
    }

    public void unregister(IMessage message){
        this.message = null;
    }

    private static MessageManager greenManager;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        if (greenManager == null) {
            synchronized (MessageManager.class){
                if (greenManager == null) {
                    greenManager = new MessageManager();
                }
            }
        }
        return greenManager;
    }


}
