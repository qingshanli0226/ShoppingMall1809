package com.example.user.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.Constants;
import com.example.common.SpUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.manager.LoginManager;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserService extends Service {
    public UserService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class  MyBind extends Binder{
        public UserService getMyService(){
            return UserService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    if (!SpUtil.getString(this).equals("")){
        RetrofitManager.getHttpApiService()
                .getAutoLogin(SpUtil.getString(this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getResult()!= null){
                            LogUtils.json(loginBean.getResult().getToken()+"User");
                            SpUtil.putString(UserService.this,loginBean.getResult().getToken());
                            LoginManager.getInstance().setLoginstate(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
        return super.onStartCommand(intent, flags, startId);
    }
}
