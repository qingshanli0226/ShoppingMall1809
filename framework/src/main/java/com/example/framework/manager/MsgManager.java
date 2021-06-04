package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.framework.db.DaoSession;
import com.example.framework.db.DbManager;
import com.example.framework.db.DbTable;
import com.example.net.AppMoudel;

import java.util.ArrayList;
import java.util.List;

public class MsgManager {

    private static MsgManager msgManager;
    private List<DbTable> messageBean = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private int count = -1;
    private DaoSession daoSession;
    private List<IMessageListenner> listenners=new ArrayList<>();

    //单例
    public static MsgManager getInstance() {
        if (msgManager == null) {
            synchronized (MsgManager.class) {
                if (msgManager == null) {
                    msgManager = new MsgManager();
                }
            }
        }
        return msgManager;
    }

    //初始化
    public void init() {
        sharedPreferences = AppMoudel.getContext().getSharedPreferences("msg.txt", Context.MODE_PRIVATE);
        daoSession = DbManager.getDbManager().getDaoSession();
    }


    public List<DbTable> getMessageBean() {
        getMsg();
        return messageBean;
    }

    public void setMessageBean(List<DbTable> messageBean) {
        this.messageBean = messageBean;
    }

    //获取消息个数
    public int getMsgCount() {
        count = sharedPreferences.getInt("msgCount", -1);
        if (count == -1) {
            return 0;
        }
        return count;
    }

    //添加消息
    public void addMsg(DbTable dbTable) {
        daoSession.insert(dbTable);
        int msgCount = getMsgCount();
        msgCount++;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("msgCount",msgCount);
        edit.commit();
    }

    //删除消息
    public void removeMsg(DbTable dbTable) {
        daoSession.delete(dbTable);
        int msgCount = getMsgCount();
        msgCount--;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("msgCount",msgCount);
        edit.commit();
    }

    //获取消息
    public void getMsg() {
        setMessageBean(daoSession.loadAll(DbTable.class));
    }

    //修改消息
    public void updataMsg(DbTable dbTable) {
        daoSession.update(dbTable);
    }



    public interface IMessageListenner {
        void onShowMsg(int count);
    }

    public void registerIMsg(IMessageListenner iMessageListenner){
        listenners.add(iMessageListenner);
    }
    public void unregisterIMsg(IMessageListenner iMessageListenner){
        listenners.remove(iMessageListenner);
    }

    public void refreshMsg(){
        for (IMessageListenner li :
                listenners) {
            li.onShowMsg(getMsgCount());
        }
    }

}
