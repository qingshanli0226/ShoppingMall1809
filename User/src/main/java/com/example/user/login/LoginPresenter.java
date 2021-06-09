package com.example.user.login;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.LogBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public
class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView iLoginView) {
        attachView(iLoginView);
    }

    public void postLogin(String name,String pwd){


        RetrofitCreate.getFiannceApiService()
                .postLogin(name, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (IView!=null){
                            IView.showLoading();
                            add(disposable);
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull LogBean logBean) {
                        if (IView!=null){
                            IView.onLoginData(logBean);
                            LogUtils.json(logBean);
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
