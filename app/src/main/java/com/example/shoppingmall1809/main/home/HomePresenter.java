package com.example.shoppingmall1809.main.home;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.HoemBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(IHomeView iHomeView) {
        attachView(iHomeView);
    }
    public void getHomeData(){
        RetrofitCreator.getShopApiService().getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                    if (iView != null) {
                        iView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (iView != null) {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<HoemBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HoemBean hoemBean) {
                        if (iView != null) {
                            iView.onHomeData(hoemBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.Error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
