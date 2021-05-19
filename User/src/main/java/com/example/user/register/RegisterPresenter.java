package com.example.user.register;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.LogBean;
import com.example.common.bean.RegBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;
import com.example.user.login.ILoginView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public
class RegisterPresenter extends BasePresenter<IRegisterView> {

    public RegisterPresenter(IRegisterView iRegisterView) {
        attachView(iRegisterView);
    }

    public void postRegister(String name,String pwd){

        RetrofitCreate.getFiannceApiService()
                .postRegister(name, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull RegBean regBean) {
                        if (IView!=null){
                            IView.onRegisterData(regBean);
                            LogUtils.json(regBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            Log.i("zrf", "onError: "+e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
