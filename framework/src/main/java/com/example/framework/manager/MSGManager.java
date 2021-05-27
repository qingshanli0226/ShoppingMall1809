package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.example.framework.greendao.CacheMessage;
import com.example.framework.greendao.CacheMessageDao;
import com.example.framework.greendao.DaoMaster;
import com.example.framework.greendao.DaoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MSGManager {
    private static MSGManager msgManager;
    private  Context context;
    private final String SQL_NAME="msg.db";
    private final String MESSAGE_SP_NAME="msg";
    private final String MESSAGE_SP_COUNT="msgCount";
    private CacheMessageDao messageBeanDao;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ExecutorService executorService= Executors.newCachedThreadPool();
    private Handler handler=new Handler();
    private List<CacheMessage> messageBeans=new ArrayList<>();

    public static MSGManager getInstance() {
        if(msgManager==null){
            msgManager=new MSGManager();
        }
        return msgManager;
    }

    private MSGManager() {

    }

    public void init(Context context){
        this.context=context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, SQL_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        messageBeanDao = daoSession.getCacheMessageDao();
        sharedPreferences = context.getSharedPreferences(MESSAGE_SP_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        queryMessage(new IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<CacheMessage> messageBeanList) {
               messageBeans.addAll(messageBeanList);
            }
        });
    }
    public void updateMessageCount(int count){
        editor.putInt(MESSAGE_SP_COUNT,count);
        editor.commit();
    }
    public  int getMessageCount(){
        return sharedPreferences.getInt(MESSAGE_SP_COUNT,0);
    }
    public void addMessage(@NonNull final CacheMessage messageBean, final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                messageBeanDao.insert(messageBean);
                updateMessageCount(getMessageCount()+1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(messageListener!=null){
                            messageBeans.add(0,messageBean);
                            messageListener.onResult(true,null);

                        }
                    }
                });
            }
        });
    }
    public void deleteMessage(@NonNull final CacheMessage messageBean, final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                messageBeanDao.delete(messageBean);
                if(getMessageCount()>0){
                    updateMessageCount(getMessageCount()-1);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(messageListener!=null){
                            messageBeans.remove(messageBean);
                            messageListener.onResult(true,null);

                        }
                    }
                });
            }
        });

    }
    public void updateMessage(@NonNull final CacheMessage messageBean, final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                messageBeanDao.update(messageBean);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(messageListener!=null){
                            messageListener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }
    public void queryMessage(@NonNull final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final List<CacheMessage> beans = messageBeanDao.queryBuilder().orderDesc(CacheMessageDao.Properties.Time).limit(50).list();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        messageListener.onResult(true,beans);
                    }
                });
            }
        });
    }


    public List<CacheMessage> getMessageBeanList(){
        return  messageBeans;
    }

    public interface  IMessageListener{
        void onResult(boolean isSuccess, List<CacheMessage> messageBeanList);
    }
}
