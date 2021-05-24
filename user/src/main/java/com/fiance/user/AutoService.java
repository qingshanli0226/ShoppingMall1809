package com.fiance.user;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.os.UserManager;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.fiance.user.logins.ILoginView;
import com.fiance.user.logins.LoginPresenter;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.net.model.RetrofitCreate;
import com.shoppingmall.net.sp.SpUtil;
import com.shoppingmall.net.bean.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutoService extends Service {
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class ShopMallBinder extends Binder {
        public AutoService getShopMallService() {
            return AutoService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        RetrofitCreate.getShoppingMallApiService().getAuto(SpUtil.getString(AutoService.this,"token"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if(loginBean.getCode().equals("200")){
                            SpUtil.putString(AutoService.this,"token",loginBean.getResult().getToken());
                            ShopMallUserManager.getInstance().setLoginBean(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AutoService.this, "自动登录失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return super.onStartCommand(intent, flags, startId);
    }
}