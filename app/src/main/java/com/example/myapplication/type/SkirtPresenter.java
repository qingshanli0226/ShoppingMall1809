package com.example.myapplication.type;

import com.example.net.RetrofitManager;
<<<<<<< HEAD:app/src/main/java/com/example/myapplication/type/SkirtPresenter.java
import com.example.net.bean.AccessoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.HomeProductBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.Overconat;
import com.example.net.bean.PantsBean;
=======
>>>>>>> zyh:app/src/main/java/com/example/myapplication/classify/SkirtPresenter.java
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
