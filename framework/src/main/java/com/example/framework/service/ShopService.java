package com.example.framework.service;

import android.app.Application;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.commom.ShopConstants;
import com.example.commom.SpUtil;
import com.example.framework.db.MessageTable;
import com.example.framework.manager.GreenManager;
import com.example.framework.manager.ShopeUserManager;
import com.example.net.RetrofitCreator;
import com.example.net.model.LoginBean;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class ShopBinder extends Binder {
        public ShopService getShopService() {
            return ShopService.this;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String token = SpUtil.getString(this, ShopConstants.TOKEN_KEY);

        RetrofitCreator.getShopApiService().getAutoLoginData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (loginBean.getCode().equals("200")) {
                            SpUtil.setString(ShopService.this, ShopConstants.TOKEN_KEY, loginBean.getResult().getToken());
                            ShopeUserManager.getInstance().setLoginBean(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        Toast.makeText(ShopService.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        PushAgent mPushAgent = PushAgent.getInstance(this);
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                for (Map.Entry entry : msg.extra.entrySet()) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    MessageTable messageTable = new MessageTable(null, (String) value, System.currentTimeMillis() + "", true);
                    GreenManager.getInstance().setMessage(messageTable);
                    GreenManager.getInstance().addCount();
                }
                return super.getNotification(context, msg);
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        return super.onStartCommand(intent, flags, startId);
    }
}
