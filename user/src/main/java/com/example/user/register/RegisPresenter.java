package com.example.user.register;

import com.example.net.RetrofitManager;
import com.example.net.bean.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;

public class RegisPresenter extends BasePresenter<IRegisterView> {
    public RegisPresenter(IRegisterView iRegisterView) {
        attView(iRegisterView);
    }
    public void onRegister(String name,String password){
        RetrofitManager.getApi()
                .getRegister(name, password)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                             if (mView!=null){
                                 mView.OnRegister(registerBean);
                             }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                                if (mView!=null){
                                    mView.showToast(e.getMessage());
                                }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
