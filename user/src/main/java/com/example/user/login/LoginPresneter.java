package com.example.user.login;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresneter extends BasePresenter<ILoginView> {
    public LoginPresneter(ILoginView iLoginView) {
        attachView(iLoginView);
    }

    public void getRegisterData(String user, String pwd) {
        RetrofitCreator.getShopApiService()
                .getLoginData(user,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                })
                .doFinally(() -> {

                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (iView != null) {
                            iView.onLoginData(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null)
                            iView.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
