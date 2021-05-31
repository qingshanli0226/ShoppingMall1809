package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.mode.HomeBean;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeMode extends BaseViewMode<ViewModeBean<HomeBean>> {

    public void getHomeData() {
        RetrofitCreator.getFiannceApiService().getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        liveData.setValue(ViewModeBean.loading(null));
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        liveData.setValue(ViewModeBean.success(homeBean));

                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.setValue(ViewModeBean.fail(null));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
