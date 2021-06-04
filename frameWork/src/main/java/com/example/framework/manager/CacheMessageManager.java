package com.example.framework.manager;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.example.common.LogUtil;
import com.example.common.SpUtil;
import com.example.net.bean.EventBean;
import com.example.net.bean.LoginBean;
import com.fiannce.sql.DaoSession;
import com.fiannce.sql.MessageBeanDao;
import com.fiannce.sql.bean.MessageBean;
import com.fiannce.sql.manager.SqlManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CacheMessageManager {
    private static CacheMessageManager cacheMessageManager;
    private Context mContext;

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

    public void init(Context context){
        this.mContext = context;
        //更改
        searchMessage();
        if (SpUtil.getInt(mContext) == -1) {
            SpUtil.putInt(mContext,0);
        }
    }

    public int count(){
        int count = 0;
        for (MessageBean messageBean : messageBeans) {
            if(messageBean.getIsRead()){
                count++;
            }
        }
        return count;
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
    public synchronized void addMessage(MessageBean messageBean){
        //数据库
        DaoSession daoSession = SqlManager.getInstance().getDaoSession();
        daoSession.insert(messageBean);
        //缓存
        messageBeans.add(messageBean);
        //通知
        freshAdd(messageBeans.size()-1);
        AddAndSub(true);
        EventBean eventBean = new EventBean(2,SpUtil.getInt(mContext),"信息");
        EventBus.getDefault().post(eventBean);
    }

    private void AddAndSub(boolean isAddAndSub) {
        if(isAddAndSub){
            SpUtil.putInt(mContext,SpUtil.getInt(mContext)+1);
        } else{
            if(SpUtil.getInt(mContext) > 0){
                SpUtil.putInt(mContext,SpUtil.getInt(mContext)-1);
            }
        }

    }

    //设置已读
    public synchronized void setRead(int position,MessageBean messageBean){
        //数据库
        DaoSession daoSession = SqlManager.getInstance().getDaoSession();
        daoSession.update(messageBean);
        //缓存
        messageBeans.get(position).setIsRead(messageBean.getIsRead());
        freshAdd(position);
        AddAndSub(false);
        EventBean eventBean = new EventBean(2,SpUtil.getInt(mContext),"信息");
        EventBus.getDefault().post(eventBean);

    }

    //查询数据库
    public synchronized void searchMessage(){
        MessageBeanDao messageBeanDao = SqlManager.getInstance().getDaoSession().getMessageBeanDao();
        LogUtil.d("zyb"+messageBeanDao);

        DaoSession daoSession = SqlManager.getInstance().getDaoSession();
        List<MessageBean> messageBeans = daoSession.loadAll(MessageBean.class);
        this.messageBeans.clear();
        this.messageBeans.addAll(messageBeans);
        freshAll();
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
