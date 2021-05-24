package com.example.user;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.framework.SpUtil;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.LoginBean;
import com.example.user.autologin.AutoLoginPresenter;
import com.example.user.login.ILoginView;

public class AutoService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public AutoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("zyh", "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new AutoLoginPresenter(new ILoginView() {
            @Override
            public void onLogin(LoginBean loginBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                if (loginBean.getCode().equals("200")) {
                    SpUtil.setString(AutoService.this, "token", loginBean.getResult().getToken());
                    CacheUserManager.getInstance().setLoginBean(true);
                    return;
                }
                CacheUserManager.getInstance().setLoginBean(false);
            }

            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showToast(String msg) {

            }
        }).getAutoLogin(SpUtil.getString(this, "token"));
        return super.onStartCommand(intent, flags, startId);
    }
}
