package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.commom.ShopConstants;
import com.example.framework.db.DaoMaster;
import com.example.framework.db.DaoSession;
import com.example.framework.db.MessageTable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MessageManager {

    private Context context;
    private DaoSession daoSession;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private Handler handler = new Handler();

    public void init(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, ShopConstants.SQ_MANAGE);
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();

        //初始化SP
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        if (count == -1) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ShopConstants.SP_MANAGE_NAME, 0);
            edit.commit();
        }
    }

    //获取所有
    public synchronized void getMessage(IListMessageTable listMessageTable) {
        cachedThreadPool.execute(() -> {
            List<MessageTable> messageTables = daoSession.loadAll(MessageTable.class);
            if (listMessageTable != null) {
                listMessageTable.IListData(messageTables);
            }
        });
    }


    public interface IListMessageTable {
        void IListData(List<MessageTable> list);
    }

    //添加一个
    public synchronized void setMessage(MessageTable message) {
        cachedThreadPool.execute(() -> {
            daoSession.insert(message);
        });
    }

    //添加个数
    public synchronized void addCount() {
        cachedThreadPool.execute(() -> {
            SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
            int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
            if (count != -1) {
                ++count;
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt(ShopConstants.SP_MANAGE_NAME, count);
                edit.commit();
                if (message != null) {
                    int finalCount = count;
                    handler.post(() -> {
                        message.onMessage(finalCount);
                    });
                }
            }
        });
    }

    public synchronized void subCount() {
        cachedThreadPool.execute(() -> {
            SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
            int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
            if (count != -1) {
                --count;
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt(ShopConstants.SP_MANAGE_NAME, count);
                edit.commit();
                if (message != null) {
                    int finalCount = count;
                    handler.post(() -> {
                        message.onMessage(finalCount);
                    });
                }
            }
        });
    }

    //数量
    public synchronized void getCount(ICountListener iCountListener) {
        cachedThreadPool.execute(() -> {
            SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
            int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
            if (iCountListener != null) {
                handler.post(() -> {
                    iCountListener.ICount(count);
                });
            }
        });
    }

    public interface ICountListener {
        void ICount(int count);
    }

    //修改
    public synchronized void upDataMessage(MessageTable messageTable) {
        cachedThreadPool.execute(() -> {
            daoSession.update(messageTable);
        });
    }

    public interface IMessage {
        void onMessage(int count);
    }

    private IMessage message;

    public void register(IMessage message) {
        this.message = message;
    }

    public void unregister(IMessage message) {
        this.message = null;
    }

    private static MessageManager greenManager;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        if (greenManager == null) {
            synchronized (MessageManager.class) {
                if (greenManager == null) {
                    greenManager = new MessageManager();
                }
            }
        }
        return greenManager;
    }


}
