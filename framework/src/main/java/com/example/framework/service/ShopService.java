package com.example.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.commom.ShopConstants;
import com.example.commom.SpUtil;
import com.example.framework.manager.ShopeUserManager;
import com.example.net.RetrofitCreator;
import com.example.net.model.LoginBean;

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

        return super.onStartCommand(intent, flags, startId);
    }
}
