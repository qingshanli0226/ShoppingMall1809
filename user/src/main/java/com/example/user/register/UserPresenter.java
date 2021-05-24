package com.example.user.register;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.user.LoginBean;
import com.example.net.bean.user.RegisterBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends BasePresenter<IUserView> {
    public UserPresenter(IUserView iRegisterView) {
        attachView(iRegisterView);
    }

    public void getRegister(String name, String password) {
        RetrofitCreator.getFiannceApiService()
                .getRegisterData(name, password)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onRegister(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.showToast(e.getMessage().toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getLogin(String name, String password) {
        RetrofitCreator.getFiannceApiService()
                .getLoginData(name, password)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (iView != null) {
                            iView.onLogin(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getAutoLogin(String token) {
        RetrofitCreator.getFiannceApiService()
                .getAutoLogin(token)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (iView != null) {
                            iView.onAutoLogin(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
