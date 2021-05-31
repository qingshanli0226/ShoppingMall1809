package com.example.framework.manager;

import com.example.common.LogUtil;
import com.fiannce.sql.DaoSession;
import com.fiannce.sql.MessageBeanDao;
import com.fiannce.sql.bean.MessageBean;
import com.fiannce.sql.manager.SqlManager;

import java.util.ArrayList;
import java.util.List;

public class CacheMessageManager {
    private static CacheMessageManager cacheMessageManager;

    private CacheMessageManager() {
    }

    public synchronized static CacheMessageManager getInstance() {
        if(cacheMessageManager == null){
            synchronized (CacheMessageManager.class){
                if(cacheMessageManager == null) {
                    cacheMessageManager = new CacheMessageManager();
                }
            }
        }
        return cacheMessageManager;
    }

    //信息列表
    private List<MessageBean> messageBeans = new ArrayList<>();
    private List<IMessageListener> messageListeners = new ArrayList<>();

    public List<MessageBean> getMessageBeans() {
        return messageBeans;
    }

    //注册
    public synchronized void register(IMessageListener messageListener){
        messageListeners.add(messageListener);
    }
    //取消注册
    public synchronized void unRegister(IMessageListener messageListener){
        messageListeners.remove(messageListener);
    }

    //添加
    public void addMessage(MessageBean messageBean){
        //数据库
        DaoSession daoSession = SqlManager.getInstance().getDaoSession();
        daoSession.insert(messageBean);
        //缓存
        messageBeans.add(messageBean);
        freshAdd(messageBeans.size()-1);
        LogUtil.d("zyb"+messageBeans);
    }

    //已读未读
    public void setRead(int position,MessageBean messageBean){
        //数据库
        DaoSession daoSession = SqlManager.getInstance().getDaoSession();
        daoSession.update(messageBean);
        //缓存
        messageBeans.remove(position);
        messageBeans.add(position,messageBean);
        freshAdd(position);
        LogUtil.d("zyb"+messageBeans);

    }

    //查询数据库
    public void searchMessage(){

        MessageBeanDao messageBeanDao = SqlManager.getInstance().getDaoSession().getMessageBeanDao();
        LogUtil.d("zyb"+messageBeanDao);

        DaoSession daoSession = SqlManager.getInstance().getDaoSession();
        List<MessageBean> messageBeans = daoSession.loadAll(MessageBean.class);
        this.messageBeans.clear();
        this.messageBeans.addAll(messageBeans);
        freshAll();
        LogUtil.d("zyb"+messageBeans);
    }

    //刷新一个
    public void freshAdd(int position){
        for (IMessageListener messageListener : messageListeners) {
            messageListener.onAddRefresh(position);
        }
    }
    //刷新全部
    public void freshAll(){
        for (IMessageListener messageListener : messageListeners) {
            messageListener.onAllRefresh(messageBeans);
        }
    }
    public interface IMessageListener{
        void onAddRefresh(int position);
        void onAllRefresh(List<MessageBean> messageBeans);
    }
}
