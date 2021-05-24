package com.example.user.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.commened.FiannceContants;
import com.example.commened.SpUtil;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.user.LoginBean;
import com.example.net.bean.user.RegisterBean;
import com.example.user.register.IUserView;
import com.example.user.register.UserPresenter;

public class AutoService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new AutoBinder();
    }

    public class AutoBinder extends Binder{
        public AutoService getAutoService() {
            return AutoService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new UserPresenter(new IUserView() {
            @Override
            public void onRegister(RegisterBean registerBean) {

            }

            @Override
            public void onLogin(LoginBean loginBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                if (loginBean.getResult() != null) {
                    SpUtil.putString(AutoService.this, FiannceContants.TOKEN_KEY, loginBean.getResult().getToken());
                    CacheUserManager.getInstance().setLoginBean(loginBean);
                }
            }

            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showToast(String error) {

            }
        }).getAutoLogin(SpUtil.getString(this, FiannceContants.TOKEN_KEY));

    }


}
