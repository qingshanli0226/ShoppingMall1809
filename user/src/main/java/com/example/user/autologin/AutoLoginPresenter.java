package com.example.user.autologin;

import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.example.user.login.ILoginView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;

public class AutoLoginPresenter extends BasePresenter<ILoginView> {
    public AutoLoginPresenter(ILoginView iLoginView) {
        attView(iLoginView);
    }
    public void getAutoLogin(String token){
        RetrofitManager.getApi()
                .getAutoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (mView!=null){
                            mView.onLogin(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
