package com.shoppingmall.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.fiance.commom.ShopmallConstants;
import com.fiance.commom.SpUtil;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.net.NetModel;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.model.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String string = SpUtil.getString(LoginService.this, ShopmallConstants.TOKEN_KEY);

        LogUtils.e(string);

        RetrofitCreate.getShoppingMallApiService()
                .getAuto(string)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        ShopMallUserManager.getInstance()
                                .setLoginBean(loginBean);

                        SpUtil.setString(NetModel.context,loginBean.getResult().getToken());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return super.onStartCommand(intent, flags, startId);
    }
}
