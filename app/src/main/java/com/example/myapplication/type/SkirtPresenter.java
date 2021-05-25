package com.example.myapplication.type;

import com.example.net.RetrofitManager;

import com.example.net.bean.SkirtBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;

public class SkirtPresenter extends BasePresenter<ISkirtView> {

    public SkirtPresenter(ISkirtView iSkirtView) {
        attView(iSkirtView);
    }

    public void onSkirt(String url) {
        RetrofitManager.getApi()
                .getSkirt(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                    mView.showLoading();
                })
                .doFinally(() -> mView.hideLoading())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull SkirtBean skirtBean) {
                        if (mView != null) {
                            mView.onSkirt(skirtBean);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
