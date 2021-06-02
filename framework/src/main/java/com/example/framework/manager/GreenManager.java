package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.ScriptC;

import com.example.commom.ShopConstants;
import com.example.framework.db.DaoMaster;
import com.example.framework.db.DaoSession;
import com.example.framework.db.MessageTable;
import com.example.framework.db.MessageTableDao;

import java.util.List;


public class GreenManager {

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

    public synchronized int getCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        return count;
    }

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

    private static GreenManager greenManager;

    private GreenManager() {
    }

    public static GreenManager getInstance() {
        if (greenManager == null) {
            greenManager = new GreenManager();
        }
        return greenManager;
    }


}
