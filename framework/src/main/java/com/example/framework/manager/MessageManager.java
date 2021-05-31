package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.example.commened.FiannceContants;
import com.example.framework.greendao.CacheMessage;
import com.example.framework.greendao.CacheMessageDao;
import com.example.framework.greendao.DaoMaster;
import com.example.framework.greendao.DaoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageManager {
    private Context context;
    private DaoSession daoSession;

    public void init(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, FiannceContants.SQ_MANAGE);
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();

        //初始化SP
        SharedPreferences sharedPreferences = context.getSharedPreferences(FiannceContants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(FiannceContants.SP_MANAGE_NAME, -1);
        if (count==-1){
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(FiannceContants.SP_MANAGE_NAME,0);
            edit.commit();
        }
    }

    //获取所有
    public List<CacheMessage>  getMessage() {
        return  daoSession.loadAll(CacheMessage.class);
    }
    //添加一个
    public void setMessage(CacheMessage message) {
        daoSession.insert(message);
    }

    public boolean addCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FiannceContants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(FiannceContants.SP_MANAGE_NAME, -1);
        if (count!=-1){
            ++count;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(FiannceContants.SP_MANAGE_NAME,count);
            edit.commit();
            if (message != null) {
                message.onMessage(count);
            }
            return true;
        }
        return false;
    }

    public boolean subCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FiannceContants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(FiannceContants.SP_MANAGE_NAME, -1);
        if (count!=-1){
            --count;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(FiannceContants.SP_MANAGE_NAME,count);
            edit.commit();
            if (message != null) {
                message.onMessage(count);
            }
            return true;
        }
        return false;
    }

    public int getCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FiannceContants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(FiannceContants.SP_MANAGE_NAME, -1);
        return count;
    }

    public void upDataMessage(CacheMessage messageTable){
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

    private static MessageManager messageManager;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        if (messageManager == null) {
            messageManager = new MessageManager();
        }
        return messageManager;
    }


}
