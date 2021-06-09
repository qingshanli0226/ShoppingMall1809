package com.example.user.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;


import com.example.common.SignUtil;
import com.example.common.SpUtil;
import com.example.framework.manager.CacheUserManager;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutoService extends Service {
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class  MyBind extends Binder{
        public AutoService getMyService(){
            return AutoService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    if (!SpUtil.getString(this).equals("")){
        String string = SpUtil.getString(this);
//        TreeMap<String, String> treeMap = new TreeMap<>();
//        treeMap.put("token",string);
//        String sign = SignUtil.generateSign(treeMap);
//        treeMap.put("sign",sign);
//        TreeMap<String, String> map = SignUtil.encryptParamsByBase64(treeMap);

        RetrofitManager.getHttpApiService()
                .getAutoLogin(string)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getResult()!= null){
                            if(loginBean.getCode().equals("200")){
                                Toast.makeText(AutoService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                                SpUtil.putString(AutoService.this,loginBean.getResult().getToken());
                                CacheUserManager.getInstance().setLoginBean(loginBean);

                            }
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
