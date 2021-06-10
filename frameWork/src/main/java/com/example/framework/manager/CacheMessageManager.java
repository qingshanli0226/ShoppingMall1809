package com.example.framework.manager;

import android.app.Notification;
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
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheMessageManager {
    private static CacheMessageManager cacheMessageManager;
    private Context mContext;

    private CacheMessageManager() {
    }

    public static CacheMessageManager getInstance() {
        if (cacheMessageManager == null) {
            synchronized (CacheMessageManager.class) {
                if (cacheMessageManager == null) {
                    cacheMessageManager = new CacheMessageManager();
                }
            }
        }
        return cacheMessageManager;
    }


    //信息列表
    private List<MessageBean> messageBeans = new ArrayList<>();
    private List<IMessageListener> messageListeners = new ArrayList<>();
    private ExecutorService executorServices = Executors.newCachedThreadPool();

    public List<MessageBean> getMessageBeans() {
        return messageBeans;
    }


    public void init(Context context){

        this.mContext = context;
        searchMessage();
        if (SpUtil.getInt(mContext) == -1) {
            SpUtil.putInt(mContext, 0);
        }

        executorServices.execute(new Runnable() {
            @Override
            public void run() {
                PushAgent pushAgent = PushAgent.getInstance(mContext);
                //接受信息
                UmengMessageHandler messageHandler = new UmengMessageHandler() {
                    @Override
                    public Notification getNotification(Context context, UMessage msg) {
                        for (Map.Entry entry : msg.extra.entrySet()) {
                            MessageBean messageBean = new MessageBean();
                            messageBean.setId(null);
                            messageBean.setType(1);
                            messageBean.setMessage(entry.getValue() + "");
                            messageBean.setMessageTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(System.currentTimeMillis()));
                            messageBean.setIsRead(true);
                            addMessage(messageBean);
                        }
                        return super.getNotification(context, msg);
                    }
                };
                pushAgent.setMessageHandler(messageHandler);
            }
        });
    }


    //注册
    public synchronized void register(IMessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    //取消注册
    public synchronized void unRegister(IMessageListener messageListener) {
        messageListeners.remove(messageListener);
    }


    //添加
    public synchronized void addMessage(MessageBean messageBean) {
        executorServices.execute(new Runnable() {
            @Override
            public void run() {
                //数据库
                MessageBeanDao messageBeanDao = SqlManager.getInstance().getDaoSession().getMessageBeanDao();
                messageBeanDao.insert(messageBean);
                //缓存
                messageBeans.add(messageBean);
                //通知
                freshAdd(messageBeans.size() - 1);
                AddAndSub(true);
                EventBean eventBean = new EventBean(2, SpUtil.getInt(mContext), "信息");
                EventBus.getDefault().post(eventBean);
            }
        });
    }

    //更改sp存储
    private void AddAndSub(boolean isAddAndSub) {
        if (isAddAndSub) {
            SpUtil.putInt(mContext, SpUtil.getInt(mContext) + 1);
        } else {
            if (SpUtil.getInt(mContext) > 0) {
                SpUtil.putInt(mContext, SpUtil.getInt(mContext) - 1);
            }
        }
    }

    //设置已读
    public synchronized void setRead(int position, MessageBean messageBean) {

        //数据库
        MessageBeanDao messageBeanDao = SqlManager.getInstance().getDaoSession().getMessageBeanDao();

        messageBeanDao.update(messageBean);
        LogUtil.d("zyb" + messageBeans);
        freshAdd(position);
        AddAndSub(false);
        EventBean eventBean = new EventBean(2, SpUtil.getInt(mContext), "信息");
        EventBus.getDefault().post(eventBean);

    }

    //查询数据库
    public synchronized void searchMessage() {
        executorServices.execute(new Runnable() {
            @Override
            public void run() {
                MessageBeanDao messageBeanDao = SqlManager.getInstance().getDaoSession().getMessageBeanDao();
                List<MessageBean> messageBeanList = messageBeanDao.loadAll();
                messageBeans.clear();
                messageBeans.addAll(messageBeanList);
                freshAll();
            }
        });
    }

    //刷新一个
    public void freshAdd(int position) {
        for (IMessageListener messageListener : messageListeners) {
            messageListener.onAddRefresh(position);
        }
    }

    //刷新全部
    public void freshAll() {
        for (IMessageListener messageListener : messageListeners) {
            messageListener.onAllRefresh(messageBeans);
        }
    }

    public interface IMessageListener {
        void onAddRefresh(int position);
        void onAllRefresh(List<MessageBean> messageBeans);
    }
}
