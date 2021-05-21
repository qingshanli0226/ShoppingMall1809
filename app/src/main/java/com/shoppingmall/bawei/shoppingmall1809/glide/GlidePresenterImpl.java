package com.shoppingmall.bawei.shoppingmall1809.glide;

import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.mode.FocusBean;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GlidePresenterImpl extends GlideContract.GlidePresenter {
    @Override
    public void getFocusData() {
        RetrofitCreator.getFiannceApiService().findFocus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FocusBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FocusBean focusBean) {
                        iView.onGlide(focusBean);
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
