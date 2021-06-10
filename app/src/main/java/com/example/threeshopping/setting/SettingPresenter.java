package com.example.threeshopping.setting;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LogoutBean;
import com.example.threeshopping.welcome.IHomeView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SettingPresenter extends BasePresenter<ISettingView> {

    public SettingPresenter(ISettingView settingView)  {
        attchView(settingView);
    }
    public void getLogout(){
        RetrofitManager.getHttpApiService()
                .getLogout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogoutBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogoutBean logoutBean) {
                        if (mView!=null){
                            mView.onLogout(logoutBean);
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
}
